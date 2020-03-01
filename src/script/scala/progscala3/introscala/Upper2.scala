// src/script/scala/progscala3/introscala/Upper2.scala

object Upper2 {
  def convert(strings: String*) = strings.map(_.toUpperCase())
}

assert(Upper2.convert("Hello", "World!") == Seq("HELLO", "WORLD!"))
