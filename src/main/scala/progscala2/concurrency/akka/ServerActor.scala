// src/main/scala/progscala2/concurrency/akka/ServerActor.scala
package progscala2.concurrency.akka
import scala.util.{Try, Success, Failure}
import scala.util.control.NonFatal
import scala.concurrent.duration._
import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global
import akka.actor.{Actor, ActorLogging, ActorRef, 
  ActorSystem, Props, OneForOneStrategy, SupervisorStrategy}
import akka.pattern.ask
import akka.util.Timeout


class ServerActor extends Actor with ActorLogging {                  // <1>
  import Messages._

  implicit val timeout = Timeout(1.seconds)

  override val supervisorStrategy: SupervisorStrategy = {            // <2>
    val decider: SupervisorStrategy.Decider = {
      case WorkerActor.CrashException => SupervisorStrategy.Restart
      case NonFatal(ex) => SupervisorStrategy.Resume
    }
    OneForOneStrategy()(decider orElse super.supervisorStrategy.decider)
  }

  var workers = Vector.empty[ActorRef]                               // <3>

  def receive = initial                                              // <4>

  val initial: Receive = {                                           // <5>
    case Start(numberOfWorkers) => 
      workers = ((1 to numberOfWorkers) map makeWorker).toVector
      context become processRequests                                 // <6>
  }

  val processRequests: Receive = {                                   // <7>
    case c @ Crash(n) => workers(n % workers.size) ! c
    case DumpAll =>                                                  // <8>
      Future.fold(workers map (_ ? DumpAll))(Vector.empty[Any])(_ :+ _)
        .onComplete(askHandler("State of the workers"))
    case Dump(n) => 
      (workers(n % workers.size) ? DumpAll).map(Vector(_))
        .onComplete(askHandler(s"State of worker $n"))
    case request: Request => 
      val key = request.key.toInt
      val index = key % workers.size
      workers(index) ! request
    case Response(Success(message)) => printResult(message) 
    case Response(Failure(ex)) => printResult(s"ERROR! $ex") 
  }

  def askHandler(prefix: String): PartialFunction[Try[Any],Unit] = {
    case Success(suc) => suc match {
      case vect: Vector[_] => 
        printResult(s"$prefix:\n")
        vect foreach {
          case Response(Success(message)) => 
            printResult(s"$message")
          case Response(Failure(ex)) => 
            printResult(s"ERROR! Success received wrapping $ex")      
        }
      case _ => printResult(s"BUG! Expected a vector, got $suc")
    }
    case Failure(ex) => printResult(s"ERROR! $ex")      
  }

  protected def printResult(message: String) = {
    println(s"<< $message")
  }

  protected def makeWorker(i: Int) =
    context.actorOf(Props[WorkerActor], s"worker-$i")
}

object ServerActor {                                                 // <9>
  def make(system: ActorSystem): ActorRef =
    system.actorOf(Props[ServerActor], "server")
}
