// src/test/scala/progscala3/fp/datastructs/FilterSuite.scala
package progscala3.fp.datastructs

import munit.*

class FilterSuite extends FunSuite:

  val stateCapitals = Map(
    "Alabama" -> "Montgomery",
    "Alaska"  -> "Juneau",
    "Wyoming" -> "Cheyenne")

  test("Use filter to select collection elements to keep") {
    val mapA = stateCapitals filter(kv => kv._1.startsWith("A"))

    assert(mapA == Map(
      "Alabama" -> "Montgomery",
      "Alaska"  -> "Juneau"))
  }
