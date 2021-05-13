// src/main/scala/progscala3/concurrency/futures/FutureFold.scala
package progscala3.concurrency.futures

import scala.concurrent.{Await, Future}
import scala.concurrent.duration.*
import scala.concurrent.ExecutionContext.Implicits.global

@main def TryFutureFold =
  var accumulator = ""                                                 // <1>
  def update(s:String) = accumulator.synchronized { accumulator += s }

  val futures = (0 to 9) map {                                         // <2>
    i => Future {
      val s = i.toString                                               // <3>
      update(s)
      s
    }
  }

  val f = Future.reduceLeft(futures)((s1, s2) => s1 + s2)              // <4>
  println(f)

  val n = Await.result(f, 2.seconds)                                   // <5>
  assert(n == "0123456789")

  println(s"accumulator: $accumulator")
