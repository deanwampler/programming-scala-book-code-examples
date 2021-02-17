// src/test/scala/progscala3/forcomps/ForForeachSuite.scala
package progscala3.forcomps

import munit.*

class ForForeachSuite extends FunSuite:
  val states   = Vector("Alabama", "Alaska", "Virginia", "Wyoming")
  val expected = Vector("alabama", "alaska", "virginia", "wyoming")

  test("A 'for {}' foreach does pure side effects on collections") {

    var lower = Vector.empty[String]
    for
      s <- states
    do lower = lower :+ s.toLowerCase
    assert(lower == expected)
  }

  test("Foreach does pure side effects on collections") {
    var lower = Vector.empty[String]
    states.foreach(s => lower = lower :+ s.toLowerCase)
    assert(lower == expected)
  }
