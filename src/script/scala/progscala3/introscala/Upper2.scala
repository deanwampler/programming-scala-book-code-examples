// src/script/scala/progscala3/introscala/Upper2.scala

object Upper {
  def upper(strings: String*) = strings.map(_.toUpperCase())
}

assert(Upper.upper("Hello", "World!") == Seq("HELLO", "WORLD!"))
