// src/main/scala/ForComps/for-monadic-tries-steps.sc

import ForComps.Process
import scala.util.{ Try, Success, Failure }

val successfulTrySteps: Seq[Int => Try[Int]] = List(
  (i:Int) => Success(i + 5), 
  (i:Int) => Success(i + 10), 
  (i:Int) => Success(i + 25))
val partiallySuccessfulTrySteps: Seq[Int => Try[Int]] = List(
  (i:Int) => Success(i + 5), 
  (i:Int) => Failure(new RuntimeException("FAIL!")),
  (i:Int) => Success(i + 25))

val tryProcess = new Process[Int, Try] {
  def flatMap[T2 >: Int](
      source: Try[Int])(
      f: Int => Try[T2]): Try[T2] =
    source flatMap f
}

tryProcess.run(successfulTrySteps)(Success(0))
// Returns: scala.util.Try[Int] = Success(40)

tryProcess.run(partiallySuccessfulTrySteps)(Success(0))
// Returns: scala.util.Try[Int] = Failure(java.lang.RuntimeException: FAIL!)
