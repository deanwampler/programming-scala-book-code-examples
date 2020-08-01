// src/main/scala/progscala3/concurrency/akka/WorkerActor.scala
package progscala3.concurrency.akka
import scala.util.{Try, Success}
import akka.actor.{Actor, ActorLogging, actorRef2Scala}
import scala.language.implicitConversions

class WorkerActor extends Actor with ActorLogging:
  import Messages._

  private val datastore = collection.mutable.Map.empty[Long,String]       // <1>

  def receive =
    case KeyedRequest.Create(key, value) =>                               // <2>
      datastore += key -> value
      sender ! Response(Success(s"$key -> $value added"))
    case KeyedRequest.Read(key) =>                                        // <3>
      sender ! Response(Try(s"${datastore(key)} found for key = $key"))
    case KeyedRequest.Update(key, value) =>                               // <4>
      datastore += key -> value
      sender ! Response(Success(s"$key -> $value updated"))
    case KeyedRequest.Delete(key) =>                                      // <5>
      datastore -= key
      sender ! Response(Success(s"$key deleted"))
    case Request.Crash(_) => throw WorkerActor.CrashException             // <6>
    case Request.DumpAll =>                                               // <7>
      sender ! Response(Success(s"${self.path}: datastore = $datastore"))

object WorkerActor:
  case object CrashException extends RuntimeException("Crash!")
