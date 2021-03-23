// src/script/scala/progscala3/patternmatching/Infix.scala

infix case class And[A,B](a: A, b: B)

val and1: And[String,Int] = And("Foo", 1)
val and2: String And Int  = And("Bar", 2)
// val and3: String And Int  = "Baz" And 3  // ERROR

val results = Seq(and1, and2).map {
  case s And i => s"$s and $i"
}
assert(results == Seq("Foo and 1", "Bar and 2"))
