// src/main/scala/progscala2/forcomps/for-flatmap.sc

val states = List("Alabama", "Alaska", "Virginia", "Wyoming")

for {
  s <- states
  c <- s
} yield s"$c-${c.toUpper}"
// Results: List("A-A", "l-L", "a-A", "b-B", ...)

states flatMap (_.toSeq map (c => s"$c-${c.toUpper}"))
// Results: List("A-A", "l-L", "a-A", "b-B", ...)
