// src/script/scala/progscala3/introscala/Upper1.scala

class Upper {
  def convert(strings: Seq[String]): Seq[String] = {
    strings.map((s: String) => s.toUpperCase())
  }
}

val up = new Upper()
assert(up.convert(List("Hello", "World!")) == List("HELLO", "WORLD!"))
