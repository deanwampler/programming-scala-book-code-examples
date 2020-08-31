// src/test/scala/progscala3/implicits/CollectiveExtensionMethodsSuite.scala
package progscala3.implicits

import munit._
import CollectiveExtensionMethods._

class CollectiveExtensionMethodsSuite extends FunSuite:

  val map1 = Map("one" -> 1, "two" -> 2)
  val map2 = Map("two" -> 2, "three" -> 3)

  given Monoid[Int]:
    def add(i1: Int, i2: Int): Int = i1+i2
    def zero:Int = 0

  test("Extension method sorted returns a Seq[(K,V)] sorted by key") {
    val actual = map1.sorted
    assert(actual.equals(Vector("one" -> 1, "two" -> 2)), actual.toString)
    // assert(map1.sorted.equals(Vector("one" -> 1, "two" -> 2)))
    assert(map2.sorted.equals(Vector("three" -> 3, "two" -> 2)))
  }

  test("Extension method merge returns the map union with values 'added'") {
    assert(map1.merge(map2).equals(Map("one" -> 1, "two" -> 4, "three" -> 3)))
  }
