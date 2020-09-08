// src/script/scala-2/progscala3/rounding/YieldingFor.scala

val evens = for {
  n <- 0 to 10  // Note: 0 to 10, inclusive
  if n%2 == 0
} yield n

assert(evens == Seq(0, 2, 4, 6, 8, 10))
