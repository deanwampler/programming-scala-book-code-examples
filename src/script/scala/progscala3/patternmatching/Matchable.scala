// tag::iarray[]
// src/script/scala/progscala3/patternmatching/Matchable.scala

val iarray = IArray(1,2,3,4,5)
iarray match
  case a: Array[Int] => a(2) = 300   // Scala 3 warning!!
println(iarray)
// end::iarray[]

// tag::unbounded[]
def examine[T](seq: Seq[T]): Seq[String] = seq.map {
  case i: Int => s"Int: $i"
  case other => s"Other: $other"
}
// end::unbounded[]

// tag::bounded[]
def examine[T <: Matchable](seq: Seq[T]): Seq[String] = seq.map {
  case i: Int => s"Int: $i"
  case other => s"Other: $other"
}
val seq = Seq(1, "two", 3, 4.4)
examine(seq)
// tag::bounded[]
