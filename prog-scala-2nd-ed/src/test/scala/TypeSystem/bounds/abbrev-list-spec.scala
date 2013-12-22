// code-examples/TypeSystem/bounds/abbrev-list-spec.scala
package bounds.abbrevlist

import org.scalatest.{ FunSpec, ShouldMatchers } 

// Not very complete...
class AbbrevListSpec extends FunSpec with ShouldMatchers {

  def support = addToSusVerb("support") // add new "keyword"

  describe ("AbbrevNil") support {
    it ("item :: AbbrevNil == AbbrevList(item)") {
      val list = (1 :: AbbrevNil) 
      list.head mustEqual 1
      list.tail mustEqual AbbrevNil
    } 
    it ("AbbrevNil.foreach(...) does nothing") {
      var failed = false
      AbbrevNil.foreach { x => failed = true }
      failed must beFalse
    } 
  }

  describe ("::") support {
    it ("item :: nonEmptyAbbrevList == AbbrevList(item, ...)") {
      val list  = 1 :: 2 :: AbbrevNil
      val list2 = 3 :: list
      list2.head mustEqual 3
      list2.tail.head mustEqual 1
      list2.tail.tail.head mustEqual 2
      list2.tail.tail.tail mustEqual AbbrevNil
    } 
    it ("nonEmptyAbbrevList.foreach(...) does something for each element") {
      var count = 0
      (1 :: 2 :: AbbrevNil).foreach { x => count += 1 }
      count mustBe 2
    } 
  }
}
