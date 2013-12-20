// code-examples/TypeLessDoMore/string-util-v3.scala
// Version 4 (final) of "StringUtil" (Adds toCollection).

package typeless

object StringUtilV4 {
  def joiner(strings: List[String], separator: String): String = 
    strings.mkString(separator)
      
  def joiner(strings: List[String]): String = strings.mkString(" ")
  def joiner(strings: String*): String      = joiner(strings.toList)
  
  def toCollection(string: String) = string.split(' ')
}
