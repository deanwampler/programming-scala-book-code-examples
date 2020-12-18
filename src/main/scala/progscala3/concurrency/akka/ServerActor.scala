// src/main/scala/progscala3/concurrency/akka/ServerActor.scala
package progscala3.concurrency.akka
import akka.actor.typed.scaladsl.Behaviors
import akka.actor.typed.scaladsl.AskPattern.{Askable, schedulerFromActorSystem}
import akka.actor.typed.{ActorRef, ActorSystem, Behavior, Scheduler, SupervisorStrategy}
import akka.util.Timeout
import scala.util.{Try, Success, Failure}
import scala.util.control.NonFatal
import scala.concurrent.duration._
import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global
import scala.language.implicitConversions

object ServerActor:                   // <1>
  import Messages._

  given timeout: Timeout = Timeout(1.seconds)

  var workers = Vector.empty[ActorRef[Request]]                               // <3>

  def apply(): Behavior[Request | Response] =                                               // <4>
    Behaviors.supervise(processRequests).onFailure[RuntimeException](SupervisorStrategy.restart)

  protected def processRequests: Behavior[Request | Response] =
    Behaviors.receive { (context, message) =>
      given scheduler: Scheduler = context.system.scheduler
      message match
        case AdminRequest.Start(numberOfWorkers) =>
          workers = (1 to numberOfWorkers).toVector.map { i =>
            val name = s"worker-$i"
            context.spawn(WorkerActor(context.self, name), name)
          }
          Behaviors.same
        case c @ AdminRequest.Crash(n) =>
          workers(n % workers.size) ! c
          Behaviors.same
        case AdminRequest.DumpAll =>                                          // <8>
          (0 until workers.length).foreach { n =>
            workers(n) ! AdminRequest.Dump(n)
          }
          // (0 until workers.length).foreach { n =>
          //   workers(n).ask(replyTo => AdminRequest.Dump(n, Some(replyTo)))
          //   .onComplete(responseHandler("State of worker $n"))
          // }
          Behaviors.same
        case AdminRequest.Dump(n) =>
          val n2 = n % workers.size
          workers(n2) ! AdminRequest.Dump(n2)
          // val future = workers(n2).ask(replyTo => AdminRequest.Dump(n2, Some(replyTo)))
          // future.onComplete(responseHandler(s"State of worker $n2"))
          Behaviors.same

        case request: CRUDRequest =>
          val key = request.key.toInt
          val index = key % workers.size
          workers(index) ! request
          Behaviors.same
        case Response(Success(s)) =>
          printResult(s"$s\n")
          Behaviors.same
        case Response(Failure(th)) =>
          printResult(s"ERROR! $th")
          Behaviors.same
        // case res: Response =>
          // responseHandler(res)
          // Behaviors.same
    }
  end processRequests

  // protected def responseHandler(prefix: String = "Response"): Try[Any] => Any =
  //   case Success(Response(s @ Success)) =>
  //     printResult(s"$prefix: $s\n")
  //   case Failure(Response(f @ Failure)) =>
  //     printResult(s"ERROR! $prefix: $f")
  //   case x =>
  //     printResult(s"ERROR! $prefix: Unexpected object passed to responseHandler: $x")


  protected def printResult(message: String) =
    println(s"<< $message")

end ServerActor
