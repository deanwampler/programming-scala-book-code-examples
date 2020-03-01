// src/script/scala/progscala3/introscala/Upper1.scala

class Upper {
  def upper(strings: String*): Seq[String] = {
    strings.map((s:String) => s.toUpperCase())
  }
}

val up = new Upper
assert(up.upper("Hello", "World!") == Seq("HELLO", "WORLD!"))
