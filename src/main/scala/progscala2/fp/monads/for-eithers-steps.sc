// src/main/scala/progscala2/fp/monads/for-eithers-steps.sc

import progscala2.fp.monads.Process
import scala.util.{ Either, Left, Right }

// We need a type with a single type parameter:
type ES[T] = Either[RuntimeException,T]             // <1>

val successfulEitherSteps: Seq[Int => ES[Int]] = List(
  (i:Int) => Right(i + 5), 
  (i:Int) => Right(i + 10), 
  (i:Int) => Right(i + 25))
val partiallySuccessfulEitherSteps: Seq[Int => ES[Int]] =
 List(
  (i:Int) => Right(i + 5), 
  (i:Int) => Left(new RuntimeException("FAIL!")),
  (i:Int) => Right(i + 25))

val eitherProcess = new Process[Int, ES] {
  def flatMap[T2 >: Int](
      source: ES[Int])(
      f: Int => ES[T2]): ES[T2] =
    source.right flatMap f
}

eitherProcess.run(successfulEitherSteps)(Right(0))
// Returns: ES[Int] = Right(40)

eitherProcess.run(partiallySuccessfulEitherSteps)(Right(0))
// Returns: ES[Int] = Left(java.lang.RuntimeException: FAIL!)
