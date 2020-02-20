// src/test/scala/progscala3/forcomps/RefTransparency.scala
package progscala3.forcomps

import munit._
import progscala3.metaprogramming.requirement

object RefTransparencySuite extends FunSuite {

  test("RefTransparency.addInts implicitly converts strings to integers") {
  	for {
  	  i <- 1 to 3
  	  j <- 1 to i
  	} requirement(addInts(i.toString, j.toString) == i+j)
  }

  test("RefTransparency.addInts2 returns Right for valid int strings") {
  	requirement(addInts2("1", "2") == Right(3))
  }

  test("RefTransparency.addInts2 returns Left for invalid int strings") {
  	requirement(addInts2("1", "x") == Left("NFE: For input string: \"x\""))
    requirement(addInts2("x", "2") == Left("NFE: For input string: \"x\""))
    requirement(addInts2("x", "y") == Left("NFE: For input string: \"x\""))
  }
}
