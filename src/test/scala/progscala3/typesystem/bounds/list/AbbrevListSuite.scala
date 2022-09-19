// src/test/scala/progscala3/typesystem/bounds/list/AbbrevListSuite.scala
package progscala3.typesystem.bounds.list
import AbbrevListModule.*
import munit.*

/**
 * Test the example "AbbrevList".
 * Not very comprehensive... Should be converted to a ScalaCheck suite.
 .*/
class AbbrevListSuite extends FunSuite:

  test("item :: AbbrevNil == AbbrevList(item)") {
    val list = (1 :: AbbrevNil)
    assert(list.head == 1)
    assert(list.tail == AbbrevNil)
  }
  test("AbbrevNil.foreach(...) does nothing") {
    // If you call foreach directly on AbbrevNil, the compiler warns about dead code,
    // which is true!
    val al: AbbrevList[Any] = AbbrevNil
    al.foreach(_ => throw RuntimeException("AbbrevNil.foreach"))
  }

  test("item :: nonEmptyAbbrevList == AbbrevList(item, ...)") {
    val list  = 2 :: 3 :: AbbrevNil
    val list2 = 1 :: list
    assert(list2.head == 1)
    assert(list2.tail.head == 2)
    assert(list2.tail.tail.head == 3)
    assert(list2.tail.tail.tail == AbbrevNil)
  }
  test("nonEmptyAbbrevList.foreach(...) processes each element") {
    var count = 0
    (1 :: 2 :: AbbrevNil).foreach(i => count += i)
    assert(count == 3)
  }
