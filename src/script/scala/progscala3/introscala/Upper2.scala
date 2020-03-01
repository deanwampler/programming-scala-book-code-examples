// src/script/scala/progscala3/introscala/Upper2.scala

object Upper {
  def convert(strings: String*) = strings.map(_.toUpperCase())
}

assert(Upper.convert("Hello", "World!") == Seq("HELLO", "WORLD!"))
