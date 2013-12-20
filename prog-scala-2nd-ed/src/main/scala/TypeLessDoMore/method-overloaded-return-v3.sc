// code-examples/TypeLessDoMore/method-overloaded-return-v1-script.scala
// Version 3 of "StringUtil" (New variable argument list methods).

object StringUtilV3 {
  def joiner(strings: List[String], separator: String): String = 
    strings.mkString(separator)

  def joiner(strings: List[String]): String = joiner(strings, " ") 
  def joiner(strings: String*): String      = joiner(strings.toList)

}

println( StringUtilV1.joiner(List("Programming", "Scala")) )
