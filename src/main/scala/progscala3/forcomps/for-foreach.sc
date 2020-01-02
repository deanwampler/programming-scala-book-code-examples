// src/main/scala/progscala3/forcomps/for-foreach.sc

val states = List("Alabama", "Alaska", "Virginia", "Wyoming")

for {
  s <- states
} println(s)
// Results:
// Alabama
// Alaska
// Virginia
// Wyoming

states foreach println
// Results the same as before.
