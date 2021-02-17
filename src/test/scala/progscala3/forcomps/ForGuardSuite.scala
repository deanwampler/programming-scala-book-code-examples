// src/test/scala/progscala3/forcomps/ForGuardSuite.scala
package progscala3.forcomps

import munit.*

class ForGuardSuite extends FunSuite:
  val states = Vector("Alabama", "Alaska", "Virginia", "Wyoming")

  def doFor(): Vector[String] = for
    s <- states
    c <- s
    if c.isLower
  yield s"$c-${c.toUpper}"

  test("A guard clause can cause some iterations to be skipped") {
    val results = doFor()
    assert(results.take(4) == List("l-L", "a-A", "b-B", "a-A"))
  }

  test("A guard clause is equivalent to withFilter") {
    val results = states.flatMap(
      _.toSeq.withFilter(_.isLower).map(c => s"$c-${c.toUpper}"))
    assert(results == doFor())
  }
