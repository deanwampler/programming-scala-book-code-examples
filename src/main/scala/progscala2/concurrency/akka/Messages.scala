// src/main/scala/progscala2/concurrency/akka/Messages.scala
package progscala2.concurrency.akka
import scala.util.Try

object Messages {                                                    // <1>
  sealed trait Request {                                             // <2>
    val key: Long
  }
  case class  Create(key: Long, value: String) extends Request       // <3>
  case class  Read(key: Long) extends Request                        // <4>
  case class  Update(key: Long, value: String) extends Request       // <5>
  case class  Delete(key: Long) extends Request                      // <6>

  case class  Response(result: Try[String])                          // <7>

  case class  Start(numberOfWorkers: Int = 1)                        // <8>
  case class  Crash(whichOne: Int)                                   // <9>
  case class  Dump(whichOne: Int)                                    // <10>
  case object DumpAll
}
