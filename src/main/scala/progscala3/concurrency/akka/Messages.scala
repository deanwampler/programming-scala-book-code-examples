// src/main/scala/progscala3/concurrency/akka/Messages.scala
package progscala3.concurrency.akka
import scala.util.Try
import akka.actor.typed.ActorRef

object Messages:                                                     // <1>
  sealed trait Request                                               // <2>
  enum CRUDRequest extends Request:                                  // <3>
    val key: Long

    case Create(key: Long, value: String)
    case Read(key: Long)
    case Update(key: Long, value: String)
    case Delete(key: Long)
                                                                     // <3>
  enum AdminRequest extends Request:                                 // <4>
    case Start(numberOfWorkers: Int = 1)
    case Crash(whichOne: Int)
    case Dump(whichOne: Int)
    case DumpAll

  case class Response(result: Try[String])                           // <6>
