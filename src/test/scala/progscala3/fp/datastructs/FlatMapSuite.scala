// src/test/scala/progscala3/fp/datastructs/FlatMapSuite.scala
package progscala3.fp.datastructs

import munit.*

class FlatmapSuite extends FunSuite:

  val list = List("now", "is", "", "the", "time")

  test("Flatmap returns 0 to many elements for each input element") {
    val l1 = list flatMap (s => s.toList)
    assert(l1 ==
      List[Char]('n', 'o', 'w', 'i', 's', 't', 'h', 'e', 't', 'i', 'm', 'e'))
  }

  test("Flatmap combines mapping each element to a collection and flattening"){
    val l2 = list map (s => s.toList)
    assert(l2 ==
      List[List[Char]](List('n', 'o', 'w'), List('i', 's'), List(),
        List('t', 'h', 'e'), List('t', 'i', 'm', 'e')))
    assert(l2.flatten ==
      List[Char]('n', 'o', 'w', 'i', 's', 't', 'h', 'e', 't', 'i', 'm', 'e'))
  }
