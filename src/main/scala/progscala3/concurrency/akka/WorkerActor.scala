// src/main/scala/progscala3/concurrency/akka/WorkerActor.scala
package progscala3.concurrency.akka
import scala.util.{Try, Success, Failure}
import akka.actor.typed.scaladsl.Behaviors
import akka.actor.typed.{ActorRef, Behavior}
import Messages._

object WorkerActor:
  def apply(
      server: ActorRef[Request | Response],
      name: String): Behavior[Request] =
    val datastore = collection.mutable.Map.empty[Long,String]         // <1>
    Behaviors.receiveMessage {
      case CRUDRequest.Create(key, value) =>                          // <2>
        datastore += key -> value
        server ! Response(Success(s"$name: $key -> $value added"))
        Behaviors.same
      case CRUDRequest.Read(key) =>                                   // <3>
        server ! Response(Try(s"$name: key = $key, ${datastore(key)} found"))
        Behaviors.same
      case CRUDRequest.Update(key, value) =>                          // <4>
        datastore += key -> value
        server ! Response(Success(s"$name: $key -> $value updated"))
        Behaviors.same
      case CRUDRequest.Delete(key) =>                                 // <5>
        datastore -= key
        server ! Response(Success(s"$name: $key deleted"))
        Behaviors.same
      case AdminRequest.Crash(n) =>
        throw WorkerActor.CrashException                             // <6>
        Behaviors.stopped
      case AdminRequest.Dump(_) | AdminRequest.DumpAll =>
        server ! Response(Success(s"$name: Dump: datastore = $datastore"))
        Behaviors.same
      case req: Request =>
        server ! Response(Failure(UnexpectedRequestException(req)))
        Behaviors.same
    }

  case object CrashException extends RuntimeException("Crash!")
  case class UnexpectedRequestException(request: Request)
    extends RuntimeException(s"Did not expect to receive $request!")
