// src/test/scala/progscala3/fp/categories/ForTriesStepsSuite.scala
package progscala3.fp.categories

import munit.*
import scala.util.{Try, Success, Failure}
import scala.annotation.tailrec

class ForTriesStepsSuite extends FunSuite:

  // Example of using try handling on an arbitrarily-long
  // sequence of functions that return Try[Int]. When the sequence
  // is arbitrary, you can't use a for comprehension.

  // Alias the function signature:
  type Step = Int => Try[Int]

  val successfulSteps: Seq[Step] = Seq(
  (i:Int) => Success(i + 5),
  (i:Int) => Success(i + 10),
  (i:Int) => Success(i + 25))
  val partiallySuccessfulSteps: Seq[Step] = Seq(
  (i:Int) => Success(i + 5),
  (i:Int) => Failure(RuntimeException("FAIL!")),
  (i:Int) => Success(i + 25))

  def sumCounts1(countSteps: Seq[Step]): Try[Int] =
    val zero: Try[Int] = Success(0)
    countSteps.foldLeft(zero) {
      (sumTry, step) => sumTry flatMap (i => step(i))
    }

  test("Folding over a sequence of Somes processes all values") {
    assert(sumCounts1(successfulSteps) == Success(40))
  }

  test("Folding over a sequence of Somes and Nones returns None") {
    sumCounts1(partiallySuccessfulSteps) match
      case Failure(re) =>
        assert(re.getMessage == "FAIL!")
      case Success(i) =>
        assert(false, s"Should have failed, but returned Success($i)")
  }

  // More verbose, but it stops the "counts" iteration at the first Failure.
  // and it doesn't create intermediate Successes:
  def sumCounts2(countSteps: Seq[Step]): Try[Int] =
    @tailrec
    def sum(accum: Int, countSteps2: Seq[Step]): Try[Int] =
      countSteps2 match
        case Nil          => Success(accum)
        case step +: tail => step(accum) match
          case f @ Failure(ex) => f
          case Success(i2)     => sum(i2, tail)
    end sum
    sum(0, countSteps)
  end sumCounts2

  test("Folding using recursion should pattern match on Tries") {
    assert(sumCounts2(successfulSteps) == Success(40))

    sumCounts2(partiallySuccessfulSteps) match
      case Failure(re) => assert(re.getMessage == "FAIL!")
      case Success(i) => assert(false, s"Should have failed, but returned $i")
  }
end ForTriesStepsSuite
