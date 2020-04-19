// src/script/scala/progscala3/typelessdomore/MethodOverloadedReturnV1.scala

object StringUtilV1 {
  def joiner(string: String, strings: String*): String =
    joiner(string +: strings)

  def joiner(strings: Seq[String]): String = strings.mkString("-")
}

assert(StringUtilV1.joiner("Programming", "Scala") ==
  "Programming-Scala")
assert(StringUtilV1.joiner(Seq("Programming", "Scala")) ==
  "Programming-Scala")
