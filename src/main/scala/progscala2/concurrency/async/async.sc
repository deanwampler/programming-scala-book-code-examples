// src/main/scala/progscala2/concurrency/async/async.sc
import scala.concurrent.{Await, Future}
import scala.concurrent.duration.Duration
import scala.async.Async.{async, await}
import scala.concurrent.ExecutionContext.Implicits.global

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
}

(-1 to 1) foreach { id =>                                            // <4>
  val fut = AsyncExample.asyncGetRecord(id)
  println(Await.result(fut, Duration.Inf))
}

