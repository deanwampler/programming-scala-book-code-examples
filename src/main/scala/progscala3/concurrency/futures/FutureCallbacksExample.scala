// src/main/scala/progscala3/concurrency/futures/FutureCallbacksExample.scala
package progscala3.concurrency.futures

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.{Try, Success, Failure}                            // <1>

case class ThatsOdd(i: Int) extends RuntimeException(                // <2>
  s"odd $i received!")

object Callbacks:
  val doComplete: Try[String] => Unit =                              // <3>
    case s as Success(_) => println(s)                                // <4>
    case f as Failure(_) => println(f)

@main def TryFuturesCallbacks =
  val futures = (0 to 9) map {                                       // <5>
    case i if i % 2 == 0 => Future.successful(i.toString)
    case i => Future.failed(ThatsOdd(i))
  }
  futures map (_ onComplete Callbacks.doComplete)                    // <6>
