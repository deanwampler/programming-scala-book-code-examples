// src/main/scala/progscala3/concurrency/akka/AkkaClient.scala
package progscala3.concurrency.akka

import akka.actor.{ActorRef, actorRef2Scala, ActorSystem}
import java.lang.{NumberFormatException => NFE}
import scala.language.implicitConversions

object AkkaClient:                                              // <1>
  import Messages._

  private var system: Option[ActorSystem] = None                // <2>

  def main(params: Array[String]): Unit =
    processParams(params.toIndexedSeq)
    val sys = ActorSystem("AkkaClient")                         // <3>
    system = Some(sys)
    val server = ServerActor.make(sys)                          // <4>
    val numberOfWorkers =                                       // <5>
      sys.settings.config.getInt("server.number-workers")
    server ! Request.Start(numberOfWorkers)                     // <6>
    processInput(server)                                        // <7>

  private def processParams(params: Seq[String]): Unit = params match
    case Nil =>
    case ("-h" | "--help") +: _ => exit(help, 0)
    case head +: _ => exit(s"Unknown input $head!\n"+help, 1)

  private def processInput(server: ActorRef): Unit =            // <8>
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

    def handleInt[R](ns: String, context: String = "")(f: Int=>R) =
      handleN(ns.toInt, ns, context)(f)
    def handleLong[R](ns: String, context: String = "")(f: Long=>R) =
      handleN(ns.toLong, ns, context)(f)

    def handleN[N:Numeric,R](n: =>N, ns:String, context:String)(f: N=>R) =
      try
        f(n)
      catch
        case _: NFE =>
          val s = if context.size > 0 then
            s"""With context "$context", expected a number, but got $ns"""
          else
            s"""Expected a number, but got $ns"""
          println(s)

    val handleLine: String => Unit =                            // <9>
      case blankRE() =>   /* do nothing */
      case "h" | "help" => println(help)
      case dumpRE(n) =>
        val msg =
          if n == null then Request.DumpAll
          else handleInt(n.trim)(Request.Dump.apply)
        server ! msg
      case badCrashRE() => missingActorNumber()
      case crashRE(n) => server ! handleInt(n)(Request.Crash.apply)
      case charNumberStringRE(c, n, s) => c match
        case "c" | "C" =>
          server ! handleLong(n)(i => KeyedRequest.Create(i, s))
        case "u" | "U" =>
          server ! handleLong(n)(i => KeyedRequest.Update(i, s))
        case "r" | "R" => unexpectedString(c, n)
        case "d" | "D" => unexpectedString(c, n)
        case _ => invalidCommand(c)

      case charNumberRE(c, n) => c match
        case "r" | "R" =>
          server ! handleLong(n)(KeyedRequest.Read.apply)
        case "d" | "D" =>
          server ! handleLong(n)(KeyedRequest.Delete.apply)
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
  end processInput

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

  private def exit(message: String, status: Int): Nothing =
    for sys <- system do sys.terminate()
    println(message)
    sys.exit(status)
end AkkaClient
