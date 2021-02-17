// src/test/scala/progscala3/forcomps/ForVariableSuite.scala
package progscala3.forcomps

import munit.*

class ForVariableSuite extends FunSuite:
  val states = List("Alabama", "Alaska", "Virginia", "Wyoming")

  test("Variables can be assigned in for comprehensions") {
    val result = for
      s <- states
      c <- s
      if c.isLower
      c2 = s"$c-${c.toUpper}"
    yield c2
    // Check the first five values:
    assert(result.take(5) == List("l-L", "a-A", "b-B", "a-A", "m-M"))
  }

  test("Variable assignmen is like a map step") {
    val result = states flatMap (_.toSeq withFilter (_.isLower) map { c =>
      val c2 = s"$c-${c.toUpper}"
      c2
    })
    // Check the first five values:
    assert(result.take(5) == List("l-L", "a-A", "b-B", "a-A", "m-M"))
  }
