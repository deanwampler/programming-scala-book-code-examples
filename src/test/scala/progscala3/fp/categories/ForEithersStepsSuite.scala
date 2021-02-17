// src/test/scala/progscala3/fp/categories/ForEithersStepsSuite.scala
package progscala3.fp.categories

import munit.*
import scala.util.{Either, Left, Right}
import scala.annotation.tailrec

class ForEithersStepsSuite extends FunSuite:

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
    (i:Int) => Left(RuntimeException("FAIL!")),
    (i:Int) => Right(i + 25))

  def sumCounts1(countSteps: Seq[Step]): Either[RuntimeException,Int] =
    val zero: Either[RuntimeException,Int] = Right(0)
    countSteps.foldLeft(zero) {
      (sumEither, step) => sumEither flatMap (i => step(i))
    }

  test("Folding over a sequence of Rights processes all values") {
    assert(sumCounts1(successfulSteps) == Right(40))
  }

  test("Folding over a sequence of Lefts and Rights returns Left") {
    sumCounts1(partiallySuccessfulSteps) match
      case Left(re) =>
        assert(re.getMessage == "FAIL!")
      case Right(i) =>
        (false, s"Should have failed, but returned Right($i)")
  }

  // More verbose, but it stops the "counts" iteration at the first Left.
  // and it doesn't create intermediate Rights:
  def sumCounts2(countSteps: Seq[Step]): Either[RuntimeException,Int] =
    @tailrec
    def sum(accum: Int, countSteps2: Seq[Step]): Either[RuntimeException,Int] =
      countSteps2 match
        case Nil          => Right(accum)
        case step +: tail => step(accum) match
          case l @ Left(x) => l
          case Right(i2)   => sum(i2, tail)
    sum(0, countSteps)

  test("Folding using recursion should pattern match on Eithers") {
    assert(sumCounts2(successfulSteps) == Right(40))

    sumCounts2(partiallySuccessfulSteps) match
      case Left(re) =>
        assert(re.getMessage == "FAIL!")
      case Right(i) =>
        assert(false, s"Should have failed, but returned $i")
  }
end ForEithersStepsSuite

