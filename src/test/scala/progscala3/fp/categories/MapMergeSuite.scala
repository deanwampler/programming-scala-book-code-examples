// src/test/scala/progscala3/fp/categories/MapMergeSuite.scala
package progscala3.fp.categories

import munit._
import MapMerge._

class MapMergeSuite extends FunSuite:

  val map1 = Map("one" -> 1, "two" -> 2)
  val map2 = Map("two" -> 2, "three" -> 3)

  given Monoid[Int]:
    def add(i1: Int, i2: Int): Int = i1+i2
    def zero:Int = 0

  test("Extension method merge returns the map union with values 'added'") {
    assert(map1.merge(map2).equals(Map("one" -> 1, "two" -> 4, "three" -> 3)))
  }
