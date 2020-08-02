// src/script/scala/progscala3/typelessdomore/MethodOverloadedReturnV2.scala

object StringUtilV2:
  def joiner(string: String, strings: String*): String =
    joiner(string +: strings)

  def joiner(strings: Seq[String], separator: String = "-"): String =
    strings.mkString(separator)

assert(StringUtilV2.joiner("Programming", "Scala") ==
  "Programming-Scala")
assert(StringUtilV2.joiner(Seq("Programming", "Scala")) ==
  "Programming-Scala")
assert(StringUtilV2.joiner(Seq("Programming", "Scala"), separator="|") ==
  "Programming|Scala")
