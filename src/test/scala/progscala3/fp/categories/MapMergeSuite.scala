// src/test/scala/progscala3/fp/categories/MapMergeSuite.scala
package progscala3.fp.categories

import munit.*
import progscala3.contexts.typeclass.given

class MapMergeSuite extends FunSuite:

  val map1 = Map("one" -> 1, "two" -> 2)
  val map2 = Map("two" -> 2, "three" -> 3)

  test("Extension method combine returns the map union with values 'added'") {
    assert(map1.combine(map2) == Map("one" -> 1, "two" -> 4, "three" -> 3))
  }
