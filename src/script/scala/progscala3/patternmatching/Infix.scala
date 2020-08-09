// src/script/scala/progscala3/patternmatching/Infix.scala

case class With[A,B](a: A, b: B)

val with1: With[String,Int] = With("Foo", 1)
val with2: String With Int  = With("Bar", 2)
// val with3: String With Int  = "Baz" With 3  // ERROR

val results = Seq(with1, with2).map {
  case s With i => s"$s with $i"
}
assert(results == Seq("Foo with 1", "Bar with 2"))
