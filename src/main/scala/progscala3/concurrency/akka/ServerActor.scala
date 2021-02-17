// tag::first[]
// src/main/scala/progscala3/concurrency/akka/ServerActor.scala
package progscala3.concurrency.akka
import akka.actor.typed.scaladsl.Behaviors
import akka.actor.typed.{ActorRef, Behavior, SupervisorStrategy}
import scala.util.Success

object ServerActor:
  import Messages.*

  var workers = Vector.empty[ActorRef[Request]]                      // <1>

  def apply(): Behavior[Request | Response] =                        // <2>
    Behaviors.supervise(processRequests)
      .onFailure[RuntimeException](SupervisorStrategy.restart)
// end::first[]

// tag::second[]
  protected def processRequests: Behavior[Request | Response] =
    Behaviors.receive { (context, message) =>                        // <1>
      message match
        case AdminRequest.Start(numberOfWorkers, replyTo) =>
          workers = (1 to numberOfWorkers).toVector.map { i =>
            val name = s"worker-$i"
            context.spawn(WorkerActor(context.self, name), name)     // <2>
          }
          replyTo ! Response(
            Success(s"Starting $numberOfWorkers workers"), replyTo)
          Behaviors.same
        case c @ AdminRequest.Crash(n, replyTo) =>                   // <3>
          val n2 = n % workers.size
          workers(n2) ! c
          replyTo ! Response(
            Success(s"Crashed worker $n2 (from n=$n)"), replyTo)
          Behaviors.same
        case AdminRequest.DumpAll(replyTo) =>                        // <4>
          (0 until workers.length).foreach { n =>
            workers(n) ! AdminRequest.DumpAll(replyTo)
          }
          Behaviors.same
        case AdminRequest.Dump(n, replyTo) =>
          val n2 = n % workers.size
          workers(n2) ! AdminRequest.Dump(n2, replyTo)
          Behaviors.same
        case request: CRUDRequest =>                                 // <5>
          val key = request.key.toInt
          val index = key % workers.size  // in case key >= workers.size
          workers(index) ! request
          Behaviors.same
        case resp @ Response(_, replyTo) =>                          // <6>
          replyTo ! resp
          Behaviors.same
    }
  end processRequests
end ServerActor
// end::second[]
