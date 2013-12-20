// code-examples/TypeLessDoMore/string-util-spec.scala

import org.specs2.mutable._ 
import StringUtil4._

object StringUtilSpec extends Specification {
  "joiner(list)" should {
    "return a string with the list items separated by a space" in {
      joiner(List("Programming", "Scala")) mustEqual "Programming Scala"
    }
  }
  "joiner(list, separator)" should {
    "return a string with the list items separated by the given separator" in {
      joiner(List("Programming", "Scala"), "|") mustEqual "Programming|Scala"
    }
  }
  "toCollection" should {
    "split a string on whitespace and return a collection of tokens" in {
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
