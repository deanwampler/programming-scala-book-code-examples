// src/test/scala/TypeLessDoMore/string-util-spec.scala

import typeless.StringUtilV4._
import org.scalatest.{ FunSpec, ShouldMatchers } 

class StringUtilSpec extends FunSpec with ShouldMatchers { 
  describe ("joiner(list)") {
    it ("return a string with the list items separated by a space") {
      joiner(List("Programming", "Scala")) shouldEqual "Programming Scala"
    }
  }
  describe ("joiner(list, separator)") {
    it ("return a string with the list items separated by the given separator") {
      joiner(List("Programming", "Scala"), "|") shouldEqual "Programming|Scala"
    }
  }
  describe ("toCollection") {
    it ("split a string on whitespace and return a collection of tokens") {
      val collection = toCollection("Now is the time for all good men")
        collection(0) shouldEqual "Now"
        collection(1) shouldEqual "is"
        collection(2) shouldEqual "the"
        collection(3) shouldEqual "time"
        collection(4) shouldEqual "for"
        collection(5) shouldEqual "all"
        collection(6) shouldEqual "good"
        collection(7) shouldEqual "men"
    }
  }
}
