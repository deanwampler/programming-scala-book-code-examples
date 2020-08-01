// src/script/scala/progscala3/introscala/Upper1.scala

class Upper1:
  def convert(strings: Seq[String]): Seq[String] =
    strings.map((s: String) => s.toUpperCase())

val up = new Upper1()
println(up.convert(List("Hello", "World!")))
