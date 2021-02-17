// src/test/scala/progscala3/forcomps/ForFlatmapSuite.scala
package progscala3.forcomps

import munit.*

class ForFlatmapSuite extends FunSuite:
  test("Flatmap returns collections and flattens them") {
    val states = List("Alabama", "Alaska", "Virginia", "Wyoming")

    val results1 = for
      s <- states
      c <- s
    yield s"$c-${c.toUpper}"
    assert(results1.take(4) == List("A-A", "l-L", "a-A", "b-B"))

    val results2 = states flatMap (_.toSeq map (c => s"$c-${c.toUpper}"))
    assert(results2 == results1)
  }
