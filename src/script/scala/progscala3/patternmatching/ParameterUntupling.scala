// src/script/scala/progscala3/patternmatching/ParameterUntupling.scala

val tuples = Seq((1,2,3), (4,5,6), (7,8,9))
val counts1 = tuples.map {
  case (x, y, z) => x + y + z
}
val counts2 = tuples.map {
  (x, y, z) => x + y + z
}
val counts3 = tuples.map(_+_+_)

assert(counts1 == List(6, 15, 24))
assert(counts2 == List(6, 15, 24))
assert(counts3 == List(6, 15, 24))

val tuples2 = Seq((1,(2,3)), (4,(5,6)), (7,(8,9)))
val counts2b = tuples2.map {
  (x, (y, z)) => x + y + z      // Untupling doesn't work for nested tuples
}
