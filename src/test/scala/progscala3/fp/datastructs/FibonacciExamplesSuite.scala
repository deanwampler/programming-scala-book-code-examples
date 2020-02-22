// src/test/scala/progscala3/fp/datastructs/FibonacciExamplesSuite.scala
package progscala3.fp.datastructs

import munit._
import scala.math.BigInt
import scala.language.implicitConversions

class FibonacciExamplesSuite extends FunSuite {

	test("Compute Fibonacci numbers the lazy way!") {
		// Must convert to an "eager" sequence to see the values:
		assert(Fibonacci.fibs.take(10).force ==
			LazyList(0, 1, 1, 2, 3, 5, 8, 13, 21, 34))
	}
}

/**
 * Trying to define recursive value for "fibs" in the class fails
 * for Scala 3, but works in an object.
 */
object Fibonacci {
  val fibs: LazyList[BigInt] =
    BigInt(0) #:: BigInt(1) #:: fibs.zip(fibs.tail).map (n => n._1 + n._2)
}