// src/main/scala/IntroScala/upper1.sc

class Upper {
  // BEGIN UPPPER_METHOD
  def upper(strings: String*): Seq[String] = {
  // END UPPPER_METHOD
    // BEGIN ANON_FUNC
    strings.map((s:String) => s.toUpperCase())
    // END ANON_FUNC
  }
}

val up = new Upper
println(up.upper("Hello", "World!"))
