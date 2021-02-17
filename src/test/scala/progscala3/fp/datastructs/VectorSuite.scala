// src/test/scala/progscala3/fp/datastructs/VectorSuite.scala
package progscala3.fp.datastructs

import munit.*

class VectorSuite extends FunSuite:

  val vect = Vector("Programming", "Scala")

  test("Construct a vectoror with the Vector() constructor") {
    assert(vect.size == 2)
  }

  test("Construct a vector with the x +: y +: vect concatenation") {
    val vect1 = "People" +: "should" +: "read" +: vect
    assert(vect1 ==
      Vector("People", "should", "read", "Programming", "Scala"))
  }

  test("Construct a vector with the vect :+ x :+ y concatenation") {
    val vect1 = vect :+ "People" :+ "should" :+ "read"
    assert(vect1 ==
      Vector("Programming", "Scala", "People", "should", "read"))
  }

  test("Construct a vector with the x +: y +: Nil construction") {
    val vect = "Programming" +: "Scala" +: Nil
    assert(vect.size == 2)
  }

  test("Join vectors with ++") {
    val vect1 = "People" +: "should" +: "read" +: Nil
    val vect2 = vect1 ++ vect
    assert(vect2 ==
      Vector("People", "should", "read", "Programming", "Scala"))
  }
