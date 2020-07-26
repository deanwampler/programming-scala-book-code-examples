// src/main/scala/progscala3/concurrency/akka/Messages.scala
package progscala3.concurrency.akka
import scala.util.Try

object Messages {                                // <1>
  enum KeyedRequest {                            // <2>
    val key: Long

    case Create(key: Long, value: String)        // <3>
    case Read(key: Long)                         // <4>
    case Update(key: Long, value: String)        // <5>
    case Delete(key: Long)                       // <6>
  }

  case class Response(result: Try[String])       // <7>

  enum Request {
    case Start(numberOfWorkers: Int = 1)         // <8>
    case Crash(whichOne: Int)                    // <9>
    case Dump(whichOne: Int)                     // <10>
    case DumpAll
  }
}
