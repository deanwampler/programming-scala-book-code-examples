// code-examples/TypeLessDoMore/string-util-v1-script.scala
// Version 1 of "StringUtil".

object StringUtil {
  def joiner(strings: List[String], separator: String): String = 
    strings.mkString(separator)

  def joiner(strings: List[String]): String = joiner(strings, " ")
}
import StringUtil._  // Import the joiner methods.

println( joiner(List("Programming", "Scala")) )