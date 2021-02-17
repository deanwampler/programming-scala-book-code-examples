// src/test/scala/progscala3/fp/datastructs/FoldReduceSuite.scala
package progscala3.fp.datastructs

import munit.*
import java.lang.UnsupportedOperationException

class FoldReduceSuite extends FunSuite:

  test("Reduce converts a collection of A to an A") {
    val result = List(1,2,3,4,5,6).reduce (_ + _)
    assert(result == 21)
  }

  test("Fold converts a collection of A to any B, starting with a seed"){
    val result = List(1,2,3,4,5,6).fold(0)(_ + _)
    assert(result == 21)
  }

  test("Reducing an empty collection throws an exception") {
    intercept[UnsupportedOperationException] {
      List.empty[Int].reduce(_ + _)
    }
  }

  test("Reducing an empty collection with reduceOption returns None") {
    val result = List.empty[Int].reduceOption(_ + _)
    assert(result == None)
  }

  test("Reducing a non-empty collection with reduceOption returns Some(...)") {
    val r4 = List(1,2,3,4,5,6).reduceOption(_ * _)
    assert(r4 == Some(720))
  }
