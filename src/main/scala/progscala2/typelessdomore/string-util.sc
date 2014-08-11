// src/main/scala/progscala2/typelessdomore/string-util.sc

// We'll cheat and use one of the "Seq.mkString" methods.
object StringUtil {
  def joiner(strings: Seq[String], separator: String = " "): String= // <1>
    strings.mkString(separator)

  /** 
   * Variable argument list, which means the user can't specify
   * a separator argument.
   */
  def joiner(strings: String*): String = joiner(strings.toSeq)       // <2>
}

import StringUtil._  // Import the joiner methods.

println(joiner(List("Programming", "Scala"), "=+="))
println(joiner("Programming", "Scala", "Second", "Edition"))
