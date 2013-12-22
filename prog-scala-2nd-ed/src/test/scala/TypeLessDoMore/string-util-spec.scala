// code-examples/TypeLessDoMore/string-util-spec.scala

import org.scalatest.{ FunSpec, ShouldMatchers } 
import StringUtil4._

class StringUtilSpec extends FunSpec with ShouldMatchers {
  describe ("joiner(list)") {
    it ("return a string with the list items separated by a space") {
      joiner(List("Programming", "Scala")) mustEqual "Programming Scala"
    }
  }
  describe ("joiner(list, separator)") {
    it ("return a string with the list items separated by the given separator") {
      joiner(List("Programming", "Scala"), "|") mustEqual "Programming|Scala"
    }
  }
  describe ("toCollection") {
    it ("split a string on whitespace and return a collection of tokens") {
      val collection = toCollection("Now is the time for all good men")
        collection(0) mustEqual "Now"
        collection(1) mustEqual "is"
        collection(2) mustEqual "the"
        collection(3) mustEqual "time"
        collection(4) mustEqual "for"
        collection(5) mustEqual "all"
        collection(6) mustEqual "good"
        collection(7) mustEqual "men"
    }
  }
}
