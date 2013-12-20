// code-examples/TypeLessDoMore/string-util-v2-v28-script.scala
// Version 2 of "StringUtil" for Scala v2.8 only.

object StringUtil {
  def joiner(strings: List[String], separator: String = " "): String = 
    strings.mkString(separator)
}
import StringUtil._  // Import the joiner methods.

println(joiner(List("Programming", "Scala")))