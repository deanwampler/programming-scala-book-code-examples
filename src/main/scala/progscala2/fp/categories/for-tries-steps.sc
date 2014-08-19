// src/main/scala/progscala2/fp/categories/for-tries-steps.sc
import scala.util.{ Try, Success, Failure }

// Example of using try handling on an arbitrarily-long
// sequence of functions that return Try[Int]. When the sequence
// is arbitrary, you can't use a for comprehension.

// Alias the function signature:
type Step = Int => Try[Int]

val successfulSteps: Seq[Step] = List(
  (i:Int) => Success(i + 5),
  (i:Int) => Success(i + 10),
  (i:Int) => Success(i + 25))
val partiallySuccessfulSteps: Seq[Step] = List(
  (i:Int) => Success(i + 5),
  (i:Int) => Failure(new RuntimeException("FAIL!")),
  (i:Int) => Success(i + 25))

def sumCounts1(countSteps: Seq[Step]): Try[Int] = {
  val zero: Try[Int] = Success(0)
  (countSteps foldLeft zero) {
    (sumTry, step) => sumTry flatMap (i => step(i))
  }
}

sumCounts1(successfulSteps)
// Returns: scala.util.Try[Int] = Success(40)

sumCounts1(partiallySuccessfulSteps)
// Returns: scala.util.Try[Int] = Failure(java.lang.RuntimeException: FAIL!)

// More verbose, but it stops the "counts" iteration at the first Failure.
// and it doesn't create intermediate Successes:
def sumCounts2(countSteps: Seq[Step]): Try[Int] = {
  @annotation.tailrec
  def sum(accum: Int, countSteps2: Seq[Step]): Try[Int] =
    countSteps2 match {
      case Nil          => Success(accum)
      case step +: tail => step(accum) match {
        case f @ Failure(ex) => f
        case Success(i2)     => sum(i2, tail)
      }
    }
  sum(0, countSteps)
}

sumCounts2(successfulSteps)
// Returns: scala.util.Try[Int] = Success(40)

sumCounts2(partiallySuccessfulSteps)
// Returns: scala.util.Try[Int] = Failure(java.lang.RuntimeException: FAIL!)
