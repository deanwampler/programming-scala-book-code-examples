// src/main/scala/progscala3/concurrency/futures/FutureForComp.scala
// Not in the book. A slight variation on FutureCallbacks.scala
package progscala3.concurrency.futures2

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.{Try, Success, Failure}

case class ThatsOdd(i: Int) extends RuntimeException(
  s"odd $i received!")

val doComplete: Try[String] => Unit =
  case s: Success[String] => println(s)
  case f: Failure[String] => println(f)

def make(i: Int): Future[String] =
  if i % 2 == 0 then Future.successful(i.toString)
  else Future.failed(ThatsOdd(i))

@main def TryFuturesForComp =
  val futures = for
    i <- (0 to 9)
    future = make(i)
  yield future
  futures.map(_.onComplete(doComplete))
