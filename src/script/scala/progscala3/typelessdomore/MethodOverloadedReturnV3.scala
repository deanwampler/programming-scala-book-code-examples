// src/script/scala/progscala3/typelessdomore/MethodOverloadedReturnV3.scala
// Version 3 of "StringUtil" (New variable argument list methods).

object StringUtilV3 {
  def joiner(string: String, strings: String*): String = 
    joiner(string +: strings)

  def joiner(strings: Seq[String], separator: String = "-"): String = 
    strings.mkString(separator)
}

assert(StringUtilV3.joiner("Programming", "Scala") ==
  "Programming-Scala")
assert(StringUtilV3.joiner(Seq("Programming", "Scala")) ==
  "Programming-Scala")
assert(StringUtilV3.joiner(Seq("Programming", "Scala"), separator="|") ==
  "Programming|Scala")
