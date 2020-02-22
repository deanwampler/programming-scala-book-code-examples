// src/test/scala/progscala3/fp/curry/TupledFuncSuite.scala
package progscala3.fp.combinators

import munit._

class TupledFuncSuite extends FunSuite {

	def mult(d1: Double, d2: Double, d3: Double) = d1 * d2 * d3
	val multTupled = Function.tupled(mult)
	// multTupled: ((Double, Double, Double)) => Double = <function1>

	val d3 = (2.2, 3.3, 4.4)
	val expected = "31.9440" // use strings to avoid comparing doubles!

	test("Tuple values can be extracted and passed as arguments to functions") {
		assert(expected == toStr(mult(d3._1, d3._2, d3._3)))
	}

	test("A func of N args is convertable to a funct of 1 N-valued tuple arg") {
		val result = multTupled(d3)
		assert(expected == toStr(result))
	}

	test("A func of 1 N-valued tuple arg is convertable to a funct of N args") {
		val multUntupled = Function.untupled(multTupled)

		val result = multUntupled(d3._1, d3._2, d3._3)
		assert(expected == toStr(result))
	}

	def toStr(d: Double): String = "%7.4f".format(d) // helper
}
