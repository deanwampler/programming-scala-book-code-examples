// src/test/scala/progscala3/forcomps/ForMapSuite.scala
package progscala3.forcomps

import munit.*

class ForMapSuite extends FunSuite:
  val states   = Vector("Alabama", "Alaska", "Virginia", "Wyoming")
  val expected = Vector("ALABAMA", "ALASKA", "VIRGINIA", "WYOMING")

  test("A 'for' that maps is a one-to-one transformation") {
    val results = for
      s <- states
    yield s.toUpperCase
    assert(results == expected)
  }

  test("A map is a one-to-one transformation") {
    val results = states map (_.toUpperCase)
    assert(results == expected)
  }
