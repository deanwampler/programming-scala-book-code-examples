// src/main/scala/progscala2/patternmatching/infix.sc

case class With[A,B](a: A, b: B)

val fw1 = "Foo" With 1       // Doesn't work

// But notice the following type annotations:
val with1: With[String,Int] = With("Foo", 1)
val with2: String With Int  = With("Bar", 2)

List(with1, with2) foreach { w =>
  w match {
    case s With i => println(s"$s with $i")
    case _ => println(s"Unknown: $w")
  }
}
