// src/main/scala/progscala2/typelessdomore/method-overloaded-return-v2.sc
// Version 2 of "StringUtil" (with a fixed compilation error).

object StringUtilV2 {
  def joiner(strings: String*): String = strings.mkString("-")

  def joiner(strings: List[String]): String = joiner(strings :_*)
}

println( StringUtilV2.joiner(List("Programming", "Scala")) )
