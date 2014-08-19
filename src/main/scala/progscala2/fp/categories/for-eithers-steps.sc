// src/main/scala/progscala2/fp/categories/for-eithers-steps.sc
import scala.util.{ Either, Left, Right }

// Example of using Either handling on an arbitrarily-long
// sequence of functions that return Either[X,Int]. When the sequence
// is arbitrary, you can't use a for comprehension.

// Alias the long function signature:
type Step = Int => Either[RuntimeException,Int]

val successfulSteps: Seq[Step] = List(
  (i:Int) => Right(i + 5),
  (i:Int) => Right(i + 10),
  (i:Int) => Right(i + 25))
val partiallySuccessfulSteps: Seq[Step] = List(
  (i:Int) => Right(i + 5),
  (i:Int) => Left(new RuntimeException("FAIL!")),
  (i:Int) => Right(i + 25))

def sumCounts1(countSteps: Seq[Step]): Either[RuntimeException,Int] = {
  val zero: Either[RuntimeException,Int] = Right(0)
  (countSteps foldLeft zero) {
    (sumEither, step) => sumEither.right flatMap (i => step(i))
  }
}

sumCounts1(successfulSteps)
// Returns: .Either[RuntimeException,Int] = Right(40)

sumCounts1(partiallySuccessfulSteps)
// Returns: Either[RuntimeException,Int] = Left(RuntimeException: FAIL!)

// More verbose, but it stops the "counts" iteration at the first Left.
// and it doesn't create intermediate Rights:
def sumCounts2(countSteps: Seq[Step]): Either[RuntimeException,Int] = {
  @annotation.tailrec
  def sum(accum: Int, countSteps2: Seq[Step]): Either[RuntimeException,Int] =
    countSteps2 match {
      case Nil          => Right(accum)
      case step +: tail => step(accum) match {
        case l @ Left(x) => l
        case Right(i2)   => sum(i2, tail)
      }
    }
  sum(0, countSteps)
}

sumCounts2(successfulSteps)
// Returns: .Either[RuntimeException,Int] = Right(40)

sumCounts2(partiallySuccessfulSteps)
// Returns: Either[RuntimeException,Int] = Left(RuntimeException: FAIL!)
