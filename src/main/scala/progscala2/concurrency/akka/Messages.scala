// src/main/scala/progscala2/concurrency/akka/Messages.scala
package progscala2.concurrency.akka
import scala.util.Try

object Messages {                                                    // <1>
  sealed trait KeyedRequest {                                        // <2>
    val key: Long
  }
  sealed trait Request

  case class  Create(key: Long, value: String) extends KeyedRequest  // <3>
  case class  Read(key: Long) extends KeyedRequest                   // <4>
  case class  Update(key: Long, value: String) extends KeyedRequest  // <5>
  case class  Delete(key: Long) extends KeyedRequest                 // <6>

  case class  Response(result: Try[String])                          // <7>

  case class  Start(numberOfWorkers: Int = 1) extends Request        // <8>
  case class  Crash(whichOne: Int) extends Request                   // <9>
  case class  Dump(whichOne: Int) extends Request                    // <10>
  case object DumpAll extends Request
}
