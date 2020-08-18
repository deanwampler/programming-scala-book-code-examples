// src/script/scala/progscala3/forcomps/ForFlatmap.scala

val states = Vector("Alabama", "Alaska", "Virginia", "Wyoming")

val results1 = for
  s <- states
  c <- s
yield s"$c-${c.toUpper}"

val results2 = states.
  flatMap(s => s.toSeq).
  map(c => s"$c-${c.toUpper}")
