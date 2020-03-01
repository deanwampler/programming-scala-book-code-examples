// src/test/scala/progscala3/forcomps/RefTransparency.scala
package progscala3.forcomps

import munit._

class RefTransparencySuite extends FunSuite {
  import RefTransparency._

  test("RefTransparency.addInts implicitly converts strings to integers") {
  	for {
  	  i <- 1 to 3
  	  j <- 1 to i
  	} assert(addInts(i.toString, j.toString) == i+j)
  }

  test("RefTransparency.addInts2 returns Right for valid int strings") {
  	assert(addInts2("1", "2") == Right(3))
  }

  test("RefTransparency.addInts2 returns Left for invalid int strings") {
  	assert(addInts2("1", "x") == Left("NFE: For input string: \"x\""))
    assert(addInts2("x", "2") == Left("NFE: For input string: \"x\""))
    assert(addInts2("x", "y") == Left("NFE: For input string: \"x\""))
  }
}
