// src/main/scala/progscala3/concurrency/akka/WorkerActor.scala
package progscala3.concurrency.akka
import scala.util.{Try, Success}
import akka.actor.typed.scaladsl.Behaviors
import akka.actor.typed.{ActorRef, Behavior}
import scala.language.implicitConversions
import Messages._

object WorkerActor:
  def apply(server: ActorRef[Request | Response], name: String): Behavior[Request] =
    val datastore = collection.mutable.Map.empty[Long,String]       // <1>
    Behaviors.receiveMessage {
      case CRUDRequest.Create(key, value) =>                               // <2>
        datastore += key -> value
        server ! Response(Success(s"$name: $key -> $value added"))
        Behaviors.same
      case CRUDRequest.Read(key) =>                                        // <3>
        server ! Response(Try(s"$name: ${datastore(key)} found for key = $key"))
        Behaviors.same
      case CRUDRequest.Update(key, value) =>                               // <4>
        datastore += key -> value
        server ! Response(Success(s"$name: $key -> $value updated"))
        Behaviors.same
      case CRUDRequest.Delete(key) =>                                      // <5>
        datastore -= key
        server ! Response(Success(s"$name: $key deleted"))
        Behaviors.same
      case AdminRequest.Crash(_) =>
        throw WorkerActor.CrashException             // <6>
        Behaviors.same
      case AdminRequest.Dump(n) =>
        server ! Response(Success(s"$name ($n): datastore = $datastore"))
      // case AdminRequest.Dump(n, replyToOption) =>
      //   val (replyTo, label) = replyToOption match // <7>
      //     case Some(rt) => (rt, "input replyTo used")
      //     case None => (server, "no input replyTo supplied!")
      //   replyTo ! Response(Success(s"$name ($n - $label): datastore = $datastore"))
        Behaviors.same
    }

  case object CrashException extends RuntimeException("Crash!")
