// src/script/scala/progscala3/fp/categories/ForTriesSteps.scala
import scala.util.{ Try, Success, Failure }

type Step = Int => Try[Int]                                          // <1>

val fail = RuntimeException("FAIL!")

val successfulSteps: Seq[Step] = List(                               // <2>
  (i:Int) => Success(i + 5),
  (i:Int) => Success(i + 10),
  (i:Int) => Success(i + 25))
val partiallySuccessfulSteps: Seq[Step] = List(
  (i:Int) => Success(i + 5),
  (i:Int) => Failure(fail),
  (i:Int) => Success(i + 25))

def sumCounts(countSteps: Seq[Step]): Try[Int] =                     // <3>
  val zero: Try[Int] = Success(0)
  (countSteps foldLeft zero) {
    (sumTry, step) => sumTry.flatMap(i => step(i))
  }

assert(sumCounts(successfulSteps).equals(Success(40)))
assert(sumCounts(partiallySuccessfulSteps).equals(Failure(fail)))
