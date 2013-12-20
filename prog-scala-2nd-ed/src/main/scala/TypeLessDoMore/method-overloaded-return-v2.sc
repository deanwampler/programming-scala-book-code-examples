// code-examples/TypeLessDoMore/method-overloaded-return-v1-script.scala
// Version 2 of "StringUtil" (with a fixed compilation error).

object StringUtilV2 {
  def joiner(strings: List[String], separator: String): String = 
    strings.mkString(separator)

  def joiner(strings: List[String]): String = joiner(strings, " ") 
}

println( StringUtilV1.joiner(List("Programming", "Scala")) )
