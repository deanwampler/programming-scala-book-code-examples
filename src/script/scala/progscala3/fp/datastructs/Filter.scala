// src/script/scala/progscala3/fp/datastructs/Filter.scala

val numbers = Map("one" -> 1, "two" -> 2, "three" -> 3)

val tnumbers = numbers filter { case (k, v) => k.startsWith("t") }

assert(tnumbers == Map("two" -> 2, "three" -> 3))
