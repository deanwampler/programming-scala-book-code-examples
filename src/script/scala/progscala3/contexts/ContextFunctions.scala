// src/script/scala/progscala3/contexts/ContextFunctions.scala

import scala.concurrent.{Await, ExecutionContext, Future}
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration._

object FutureCF:
  type Executable[T] = ExecutionContext ?=> T                        // <1>

  def apply[T](body: => T): Executable[Future[T]] = Future(body)     // <2>

def sleepN(dur: Duration): Duration =                                // <3>
  val start = System.currentTimeMillis()
  Thread.sleep(dur.toMillis)
  Duration(System.currentTimeMillis - start, MILLISECONDS)

val future1 = FutureCF(sleepN(1.second))                             // <4>
val future2 = FutureCF(sleepN(1.second))(using global)               // <5>
val duration1 = Await.result(future1, 2.second)                      // <6>
val duration2 = Await.result(future2, 2.second)
