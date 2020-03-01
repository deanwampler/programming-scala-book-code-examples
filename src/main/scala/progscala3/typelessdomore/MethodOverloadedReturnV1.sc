// src/main/scala/progscala3/typelessdomore/MethodOverloadedReturnV1.sc
// Version 1 of "StringUtil" (with a compilation error).
// ERROR: Won't compile: needs a String return type on the second "joiner".

object StringUtilV1 {
  def joiner(string: String, strings: String*): String =
  	joiner(string +: strings)

  def joiner(strings: Seq[String]) = strings.mkString("-") // COMPILATION ERROR
}
