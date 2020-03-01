// src/test/scala/progscala3/fp/basics/HOFs2ExampleSuite.scala
package progscala3.fp.basics

import munit._

class HOFsExampleSuite extends FunSuite {

	test("Collections methods and their functions compose") {
		val result = (1 to 10) filter (_ % 2 == 0) map (_ * 2) reduce (_ * _)
		assert(result == 122880)
	}

	test("Closures can reference external variables") {
		var factor = 2
		val multiplier = (i: Int) => i * factor

		val result1 = (1 to 10) filter (_ % 2 == 0) map multiplier reduce (_ * _)
		assert(result1 == 122880)

		factor = 3
		val result2 = (1 to 10) filter (_ % 2 == 0) map multiplier reduce (_ * _)
		assert(result2 == 933120)
	}

	test("Functions can take functions as arguments; functions are values") {
		def m1 (multiplier: Int => Int) = {
		  (1 to 10) filter (_ % 2 == 0) map multiplier reduce (_ * _)
		}

		def m2: Int => Int = {
		  val factor = 2
		  val multiplier = (i: Int) => i * factor
		  multiplier
		}

		assert(m1(m2) == 122880)
	}

	test("Variables referenced by closures can be in objects") {
		object Multiplier {
		  var factor = 2
		  // Compare: val multiplier = (i: Int) => i * factor
		  def multiplier(i: Int) = i * factor
		}

		val result1 =
		  (1 to 10) filter (_ % 2 == 0) map Multiplier.multiplier reduce (_ * _)
		assert(result1 == 122880)

		Multiplier.factor = 3
		val result2 =
		  (1 to 10) filter (_ % 2 == 0) map Multiplier.multiplier reduce (_ * _)
		assert(result2 == 933120)
	}


}