// src/main/scala/progscala3/concurrency/futures/FutureFoldExample.scala
package progscala3.concurrency.futures

import scala.concurrent.{Await, Future}
import scala.concurrent.duration._
import scala.concurrent.ExecutionContext.Implicits.global

object FutureFoldExample {
  def main(args: Array[String]): Unit = {
		val futures = (0 to 9) map {                                         // <1>
		  i => Future {
		    val s = i.toString                                               // <2>
		    print(s)
		    s
		  }
		}

		val f = Future.reduceLeft(futures)((s1, s2) => s1 + s2)              // <3>
		println(f)

		val n = Await.result(f, 2.seconds)                                   // <4>
		assert(n == "0123456789")
	}
}
