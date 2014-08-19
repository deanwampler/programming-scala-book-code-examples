// src/test/scala/progscala2/typesystem/bounds/list/AbbrevListSpec.scala
package progscala2.typesystem.bounds.list
import org.scalatest.{ FunSpec, ShouldMatchers }

/** Test the example "AbbrevList". Not very comprehensive... */
class AbbrevListSpec extends FunSpec with ShouldMatchers {

  describe ("AbbrevNil") {
    it ("item :: AbbrevNil == AbbrevList(item)") {
      val list = (1 :: AbbrevNil)
      list.head shouldEqual 1
      list.tail shouldEqual AbbrevNil
    }
    it ("AbbrevNil.foreach(...) does nothing") {
      var failed = false
      AbbrevNil.foreach { x => failed = true }
      failed shouldEqual false
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
      (1 :: 2 :: AbbrevNil).foreach { x => count += 1 }
      count shouldEqual 2
    }
  }
}
