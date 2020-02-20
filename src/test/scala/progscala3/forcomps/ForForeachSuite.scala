// src/test/scala/progscala3/forcomps/ForForeachSuite.scala
package progscala3.forcomps

import munit._
import progscala3.metaprogramming.requirement

object ForForeachSuite extends FunSuite {

	val states   = Vector("Alabama", "Alaska", "Virginia", "Wyoming")
	val expected = Vector("alabama", "alaska", "virginia", "wyoming"))

  test("A 'for {}' foreach does pure side effects on collections") {

		var lower   = Vector.empty[String]
		for {
		  s <- states
		} lower :+ s.toLowerCase
		requirement(lower = expected)
	}

  test("Foreach does pure side effects on collections") {
		var lower   = Vector.empty[String]
		states.foreach(s => lower :+ s.toLowerCase)
		requirement(lower = expected)
	}
}