// src/main/scala/progscala3/concurrency/akka/Messages.scala
package progscala3.concurrency.akka
import scala.util.Try
import akka.actor.typed.ActorRef

object Messages:                                                // <1>
  sealed trait Request:                                         // <2>
    val replyTo: ActorRef[Response]

  enum AdminRequest extends Request:                            // <3>
    case Start(numberOfWorkers: Int = 1, replyTo: ActorRef[Response])
    case Crash(whichOne: Int, replyTo: ActorRef[Response])
    case Dump(whichOne: Int, replyTo: ActorRef[Response])
    case DumpAll(replyTo: ActorRef[Response])

  enum CRUDRequest extends Request:                             // <4>
    val key: Long
    case Create(key: Long, value: String, replyTo: ActorRef[Response])
    case Read(key: Long, replyTo: ActorRef[Response])
    case Update(key: Long, value: String, replyTo: ActorRef[Response])
    case Delete(key: Long, replyTo: ActorRef[Response])

  case class Response(                                          // <5>
    result: Try[String], replyTo: ActorRef[Response])
