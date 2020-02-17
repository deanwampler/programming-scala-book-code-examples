// src/test/scala/progscala3/typesystem/bounds/list/AbbrevListSuite.scala
package progscala3.typesystem.bounds.list

import munit._
import progscala3.metaprogramming.requirement

/**
 * Test the example "AbbrevList".
 * Not very comprehensive... Should be converted to a ScalaCheck suite.
 */
class AbbrevListSuite extends FunSuite {

  test("item :: AbbrevNil == AbbrevList(item)") {
    val list = (1 :: AbbrevNil)
    requirement(list.head == 1)
    requirement(list.tail == AbbrevNil)
  }
  test("AbbrevNil.foreach(...) does nothing") {
    // If you call foreach directly on AbbrevNil, the compiler warns about dead code,
    // which is true!
    val al: AbbrevList[Any] = AbbrevNil
    al.foreach(_ => throw new RuntimeException("AbbrevNil.foreach"))
  }

  test("item :: nonEmptyAbbrevList == AbbrevList(item, ...)") {
    val list  = 1 :: 2 :: AbbrevNil
    val list2 = 3 :: list
    requirement(list2.head == 3)
    requirement(list2.tail.head == 1)
    requirement(list2.tail.tail.head == 2)
    requirement(list2.tail.tail.tail == AbbrevNil)
  }
  test("nonEmptyAbbrevList.foreach(...) processes each element") {
    var count = 0
    (1 :: 2 :: AbbrevNil).foreach(i => count += i)
    requirement(count == 3)
  }
}
