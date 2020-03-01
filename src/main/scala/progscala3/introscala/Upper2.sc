// src/main/scala/progscala3/introscala/upper2.sc

object Upper {
  def upper(strings: String*) = strings.map(_.toUpperCase())
}

assert(Upper.upper("Hello", "World!") == Seq("HELLO", "WORLD!"))
