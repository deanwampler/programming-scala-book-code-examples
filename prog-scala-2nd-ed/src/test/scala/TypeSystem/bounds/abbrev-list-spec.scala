// code-examples/TypeSystem/bounds/abbrev-list-spec.scala
package bounds.abbrevlist

import org.specs2.mutable._ 

// Not very complete...
object AbbrevListSpec extends Specification("AbbrevList") {

  def support = addToSusVerb("support") // add new "keyword"

  "AbbrevNil" should support {
    "item :: AbbrevNil == AbbrevList(item)" in {
      val list = (1 :: AbbrevNil) 
      list.head mustEqual 1
      list.tail mustEqual AbbrevNil
    } 
    "AbbrevNil.foreach(...) does nothing" in {
      var failed = false
      AbbrevNil.foreach { x => failed = true }
      failed must beFalse
    } 
  }

  "::" should support {
    "item :: nonEmptyAbbrevList == AbbrevList(item, ...)" in {
      val list  = 1 :: 2 :: AbbrevNil
      val list2 = 3 :: list
      list2.head mustEqual 3
      list2.tail.head mustEqual 1
      list2.tail.tail.head mustEqual 2
      list2.tail.tail.tail mustEqual AbbrevNil
    } 
    "nonEmptyAbbrevList.foreach(...) does something for each element" in {
      var count = 0
      (1 :: 2 :: AbbrevNil).foreach { x => count += 1 }
      count mustBe 2
    } 
  }
}
