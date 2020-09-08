// src/main/scala/progscala3/concurrency/akka/Messages.scala
package progscala3.concurrency.akka
import scala.util.Try

object Messages:                                 // <1>
  enum KeyedRequest:                             // <2>
    val key: Long

    case Create(key: Long, value: String)        // <3>
    case Read(key: Long)
    case Update(key: Long, value: String)
    case Delete(key: Long)

  case class Response(result: Try[String])       // <4>

  enum Request:                                  // <5>
    case Start(numberOfWorkers: Int = 1)
    case Crash(whichOne: Int)
    case Dump(whichOne: Int)
    case DumpAll
