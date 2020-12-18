// src/main/scala/progscala3/concurrency/akka/AkkaClient.scala
package progscala3.concurrency.akka

import akka.actor.typed.scaladsl.Behaviors
import akka.actor.typed.{ActorRef, ActorSystem, Behavior, Terminated}
import java.lang.{NumberFormatException => NFE}
import scala.util.{Try, Success, Failure}

object AkkaClient:                                                   // <1>
  import Messages._

  private var server: ActorRef[Request] = null                       // <2>

  def main(params: Array[String]): Unit =
    processParams(params.toSeq)
    ActorSystem(AkkaClient(), "AkkaClient")                          // <3>
    processUserInput(server)

  def apply(): Behavior[Request] =
    Behaviors.setup { context =>
      val server = context.spawn(ServerActor(), "ServerActor")       // <4>
      val numberOfWorkers =                                          // <5>
        context.system.settings.config.getInt("server.number-workers")
      server ! AdminRequest.Start(numberOfWorkers)                   // <6>
      Behaviors.receiveSignal {                                      // <7>
        case (_, Terminated(_)) =>
          println("Terminated!")
          Behaviors.stopped
      }
    }

  private def processParams(params: Seq[String]): Unit =             // <8>
    params match
      case Nil =>
      case ("-h" | "--help") +: _ => exit(help, 0)
      case head +: _ => exit(s"Unknown input $head!\n"+help, 1)

  private def processUserInput(server: ActorRef[Request]): Unit =    // <9>
    val blankRE = """^\s*#?\s*$""".r
    val badCrashRE = """^\s*[Cc][Rr][Aa][Ss][Hh]\s*$""".r
    val crashRE = """^\s*[Cc][Rr][Aa][Ss][Hh]\s+(\d+)\s*$""".r
    val dumpRE = """^\s*[Dd][Uu][Mm][Pp](\s+\d+)?\s*$""".r
    val charNumberRE = """^\s*(\w)\s+(\d+)\s*$""".r
    val charNumberStringRE = """^\s*(\w)\s+(\d+)\s+(.+)$""".r

    def prompt() = print(">> ")
    def missingActorNumber() =
      println("Crash command requirements an actor number.")
    def invalidInput(s: String) =
      println(s"Unrecognized command: $s")
    def invalidCommand(c: String): Unit =
      println(s"Expected 'c', 'r', 'u', or 'd'. Got $c")
    def expectedString(): Unit =
      println("Expected a string after the command and number")
    def unexpectedString(c: String, n: String): Unit =
      println(s"Extra arguments after command and number '$c $n'")
    def finished(): Nothing = exit("Goodbye!", 0)

    def handleInt[R](ns: String)(f: Int=>R) = handleN(ns.toInt, ns)(f)
    def handleLong[R](ns: String)(f: Long=>R) = handleN(ns.toLong, ns)(f)

    def handleN[N:Numeric,R](n: =>N, ns: String)(f: N=>R): R | String =
      try f(n)
      catch
        case _: NFE => s"Expected a number, but got $ns"

    val handleLine: String => Unit =
      case blankRE() =>   /* do nothing */
      case "h" | "help" => println(help)
      case dumpRE(null) => server ! AdminRequest.DumpAll
      case dumpRE(n) => Try(n.trim.toInt) match
        case Failure(_) =>
          println(s"""String "${n.trim}" is not a number.""")
        case Success(int) =>
          server ! AdminRequest.Dump(int)
      case badCrashRE() => missingActorNumber()
      case crashRE(n) => Try(n.trim.toInt) match
        case Failure(_) =>
          println(s"""String "${n.trim}" is not a number.""")
        case Success(int) =>
          server ! AdminRequest.Crash(int)

      case charNumberStringRE(c, n, s) => c match
        case "c" | "C" => Try(n.trim.toLong) match
          case Failure(_) =>
            println(s"""String "${n.trim}" is not a number.""")
          case Success(long) =>
            server ! CRUDRequest.Create(long, s)
        case "u" | "U" => Try(n.trim.toLong) match
          case Failure(_) =>
            println(s"""String "${n.trim}" is not a number.""")
          case Success(long) =>
            server ! CRUDRequest.Update(long, s)
        case "r" | "R" => unexpectedString(c, n)
        case "d" | "D" => unexpectedString(c, n)
        case _ => invalidCommand(c)

      case charNumberRE(c, n) => c match
        case "r" | "R" => Try(n.trim.toLong) match
          case Failure(_) =>
            println(s"""String "${n.trim}" is not a number.""")
          case Success(long) => server ! CRUDRequest.Read(long)
        case "d" | "D" => Try(n.trim.toLong) match
          case Failure(_) =>
            println(s"""String "${n.trim}" is not a number.""")
          case Success(long) =>
            server ! CRUDRequest.Delete(long)
        case "c" | "C" => expectedString()
        case "u" | "U" => expectedString()
        case _ => invalidCommand(c)

      case "q" | "quit" | "exit" => finished()
      case string => invalidInput(string)
    end handleLine

    while true do
      prompt()
      Console.in.readLine() match
        case null => finished()
        case line => handleLine(line)
  end processUserInput

  private val help =
    """Usage: AkkaClient [-h | --help]
      |Then, enter one of the following commands, one per line:
      |  h | help      Print this help message.
      |  c n string    Create "record" for key n for value string.
      |  r n           Read record for key n. It's an error if n isn't found.
      |  u n string    Update (or create) record for key n for value string.
      |  d n           Delete record for key n. It's an error if n isn't found.
      |  crash n       "Crash" worker n (to test recovery).
      |  dump [n]      Dump the state of all workers (default) or worker n.
      |  ^d | q | quit Quit.
      |""".stripMargin

  private def exit(message: String, status: Int): Nothing =          // <10>
    println(message)
    sys.exit(status)
end AkkaClient
