// src/test/scala/progscala2/typesystem/bounds/list/AbbrevListSpec.scala
package progscala2.typesystem.bounds.list
import org.scalatest.{ FunSpec, Matchers }

/** Test the example "AbbrevList". Not very comprehensive... */
class AbbrevListSpec extends FunSpec with Matchers {

  describe ("AbbrevNil") {
    it ("item :: AbbrevNil == AbbrevList(item)") {
      val list = (1 :: AbbrevNil)
      list.head shouldEqual 1
      list.tail shouldEqual AbbrevNil
    }
    it ("AbbrevNil.foreach(...) does nothing") {
      // If you call foreach directly on AbbrevNil, the compiler warns about dead code,
      // which is true!
      val al: AbbrevList[Any] = AbbrevNil
      al.foreach(_ => throw new RuntimeException("AbbrevNil.foreach"))
    }
  }

  describe ("::") {
    it ("item :: nonEmptyAbbrevList == AbbrevList(item, ...)") {
      val list  = 1 :: 2 :: AbbrevNil
      val list2 = 3 :: list
      list2.head shouldEqual 3
      list2.tail.head shouldEqual 1
      list2.tail.tail.head shouldEqual 2
      list2.tail.tail.tail shouldEqual AbbrevNil
    }
    it ("nonEmptyAbbrevList.foreach(...) does something for each element") {
      var count = 0
      (1 :: 2 :: AbbrevNil).foreach(i => count += i)
      count shouldEqual 3
    }
  }
}
