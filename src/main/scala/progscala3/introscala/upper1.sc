// src/main/scala/progscala3/introscala/upper1.sc

class Upper {
  def upper(strings: String*): Seq[String] = {
    strings.map((s:String) => s.toUpperCase())
  }
}

val up = new Upper
assert(up.upper("Hello", "World!") == Seq("HELLO", "WORLD!"))
