// src/main/scala-2/progscala3/concurrency/async/Async.scala
package progscala3.concurrency.async
import scala.concurrent.{Await, Future}
import scala.concurrent.duration.Duration
import scala.async.Async.{async, await}
import scala.concurrent.ExecutionContext.Implicits.global

/**
 * This example only works with Scala 2.13, as it uses the async and await
 * macros that don't exist in Scala 3.
 * To build it you'll need the `scala-async` module:
 *   "org.scala-lang.modules" %% "scala-async" % "0.10.0"
 */
object AsyncExample {
  def recordExists(id: Long): Boolean = {                            // <1>
    println(s"recordExists($id)...")
    Thread.sleep(1)
    id > 0
  }

  def getRecord(id: Long): (Long, String) = {                        // <2>
    println(s"getRecord($id)...")
    Thread.sleep(1)
    (id, s"record: $id")
  }

  def asyncGetRecord(id: Long): Future[(Long, String)] = async {     // <3>
    val exists = async { val b = recordExists(id); println(b); b }
    if (await(exists)) await(async { val r = getRecord(id); println(r); r })
    else (id, "Record not found!")
  }

  def main(params: Array[String]): Unit = {
    (-1 to 1) foreach { id =>                                        // <4>
      val fut = AsyncExample.asyncGetRecord(id.toLong)
      println(Await.result(fut, Duration.Inf))
    }
  }
}

