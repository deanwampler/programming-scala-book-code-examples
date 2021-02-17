// src/main/scala/progscala3/concurrency/akka/WorkerActor.scala
package progscala3.concurrency.akka
import scala.util.{Try, Success, Failure}
import akka.actor.typed.scaladsl.Behaviors
import akka.actor.typed.{ActorRef, Behavior, SupervisorStrategy}
import Messages.*

object WorkerActor:

  def apply(
      server: ActorRef[Request | Response],
      name: String): Behavior[Request] =
    val datastore = collection.mutable.Map.empty[Long,String]        // <1>
    def processRequests(                                             // <2>
        server: ActorRef[Request | Response],
        name: String): Behavior[Request] =
      Behaviors.receiveMessage {
        case CRUDRequest.Create(key, value, replyTo) =>              // <3>
          datastore += key -> value
          server ! Response(Success(s"$name: $key -> $value added"), replyTo)
          Behaviors.same
        case CRUDRequest.Read(key, replyTo) =>
          server ! Response(
            Try(s"$name: key = $key, ${datastore(key)} found"), replyTo)
          Behaviors.same
        case CRUDRequest.Update(key, value, replyTo) =>
          datastore += key -> value
          server ! Response(Success(s"$name: $key -> $value updated"), replyTo)
          Behaviors.same
        case CRUDRequest.Delete(key, replyTo) =>
          datastore -= key
          server ! Response(Success(s"$name: $key deleted"), replyTo)
          Behaviors.same
        case AdminRequest.Crash(n, replyTo) =>                       // <4>
          val ex = CrashException(name)
          server ! Response(Failure(ex), replyTo)
          throw ex
          Behaviors.stopped
        case AdminRequest.Dump(n, replyTo) =>
          server ! Response(
            Success(s"$name: Dump($n): datastore = $datastore"), replyTo)
          Behaviors.same
        case AdminRequest.DumpAll(replyTo) =>
          server ! Response(
            Success(s"$name: DumpAll: datastore = $datastore"), replyTo)
          Behaviors.same
        case req: Request =>                                         // <5>
          server ! Response(
            Failure(UnexpectedRequestException(req)),req.replyTo)
          Behaviors.same
      }
    Behaviors.supervise(processRequests(server, name))               // <6>
      .onFailure[RuntimeException](SupervisorStrategy.restart)
  end apply

  case class CrashException(name: String)
    extends RuntimeException(s"$name: forced to crash!")
  case class UnexpectedRequestException(request: Request)
    extends RuntimeException(s"Did not expect to receive $request!")
