// src/main/scala/progscala3/patternmatching/infix.sc

case class With[A,B](a: A, b: B)

// This doesn't work
// val fw1 = "Foo" With 1

// But notice the following type annotations:
val with1: With[String,Int] = With("Foo", 1)
val with2: String With Int  = With("Bar", 2)

List(with1, with2) foreach { w =>
  w match {
    case s With i => println(s"$s with $i")
    case _ => assert(false, s"Unknown: $w")
  }
}
