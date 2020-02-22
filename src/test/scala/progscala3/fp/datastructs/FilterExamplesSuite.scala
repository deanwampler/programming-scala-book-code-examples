// src/test/scala/progscala3/fp/datastructs/FilterExamplesSuite.scala
package progscala3.fp.datastructs

import munit._

class FilterExamplesSuite extends FunSuite {

	val stateCapitals = Map(
	  "Alabama" -> "Montgomery",
	  "Alaska"  -> "Juneau",
	  "Wyoming" -> "Cheyenne")

	test("Use filter to select collection elements to keep") {
		val mapA = stateCapitals filter { kv => kv._1.startsWith("A") }

		assert(mapA == Map(
		  "Alabama" -> "Montgomery",
		  "Alaska"  -> "Juneau"))
	}
}
