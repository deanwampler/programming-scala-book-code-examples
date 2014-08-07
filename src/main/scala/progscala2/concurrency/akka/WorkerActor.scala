// src/main/scala/progscala2/concurrency/akka/WorkerActor.scala
package progscala2.concurrency.akka
import scala.util.{Try, Success, Failure}
import akka.actor.{Actor, ActorLogging}

class WorkerActor extends Actor with ActorLogging {
  import Messages._

  private val datastore = collection.mutable.Map.empty[Long,String]  // <1>

  def receive = {
    case Create(key, value) =>                                       // <2> 
      datastore += key -> value
      sender ! Response(Success(s"$key -> $value added"))
    case Read(key) =>                                                // <3> 
      sender ! Response(Try(s"${datastore(key)} found for key = $key"))
    case Update(key, value) =>                                       // <4>
      datastore += key -> value
      sender ! Response(Success(s"$key -> $value updated"))
    case Delete(key) =>                                              // <5>
      datastore -= key
      sender ! Response(Success(s"$key deleted"))
    case Crash(_) => throw WorkerActor.CrashException                // <6>
    case DumpAll =>                                                  // <7>
      sender ! Response(Success(s"${self.path}: datastore = $datastore"))
  }
}

object WorkerActor {
  case object CrashException extends RuntimeException("Crash!")      // <8>
}
