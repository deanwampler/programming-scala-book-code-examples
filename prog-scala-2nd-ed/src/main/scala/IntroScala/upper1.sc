// src/main/scala/IntroScala/upper1.sc

class Upper {
  def upper(strings: String*): Seq[String] = {
    // BEGIN ANON_FUNC
    strings.map((s:String) => s.toUpperCase())
    // END ANON_FUNC
  }
}

val up = new Upper
println(up.upper("Hello", "World!"))
