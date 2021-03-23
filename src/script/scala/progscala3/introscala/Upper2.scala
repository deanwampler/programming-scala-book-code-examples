// src/script/scala/progscala3/introscala/Upper2.scala

object Upper2:
  def convert(strings: Seq[String]) = strings.map(_.toUpperCase)

println(Upper2.convert(List("Hello", "World!")))
