// src/test/scala/progscala3/typesystem/bounds/list/AbbrevListSuite.scala
package progscala3.typesystem.bounds.list

import scala.language.implicitConversions
import munit._
import progscala3.metaprogramming.require

/**
 * Test the example "AbbrevList".
 * Not very comprehensive... Should be converted to a ScalaCheck suite.
 */
class AbbrevListSuite extends FunSuite {

  test("item :: AbbrevNil == AbbrevList(item)") {
    val list = (1 :: AbbrevNil)
    require(list.head == 1)
    require(list.tail == AbbrevNil)
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
    require(list2.head == 3)
    require(list2.tail.head == 1)
    require(list2.tail.tail.head == 2)
    require(list2.tail.tail.tail == AbbrevNil)
  }
  test("nonEmptyAbbrevList.foreach(...) processes each element") {
    var count = 0
    (1 :: 2 :: AbbrevNil).foreach(i => count += i)
    require(count == 3)
  }
}
