// src/test/scala/progscala3/fp/datastructs/ListSuite.scala
package progscala3.fp.datastructs

import munit.*

class ListSuite extends FunSuite:

  val list = List("Programming", "Scala")

  test("Construct a list with the List() constructor") {
    assert(list.size == 2)
  }

  test("Construct a list with the x :: y :: list concatenation") {
    val list1 = "People" :: "should" :: "read" :: list
    assert(list1 ==
      List("People", "should", "read", "Programming", "Scala"))
  }

  test("Construct a list with the x :: y :: Nil construction") {
    val list1 = "Programming" :: "Scala" :: Nil
    assert(list1.size == 2)
  }
  test("Join lists with ++") {
    val list1 = "People" :: "should" :: "read" :: Nil
    val list2 = list1 ++ list
    assert(list2 ==
      List("People", "should", "read", "Programming", "Scala"))
  }
