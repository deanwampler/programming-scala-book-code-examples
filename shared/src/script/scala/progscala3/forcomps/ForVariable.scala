// src/script/scala/progscala3/forcomps/ForVariable.scala

val states = Vector("Alabama", "Alaska", "Virginia", "Wyoming")

val results1 = for
  s <- states
  c <- s
  if c.isLower
  c2 = s"$c-${c.toUpper}"
yield c2

val results2 = states.
  flatMap(s => s.toSeq).
  withFilter(c => c.isLower).
  map(c => s"$c-${c.toUpper}")
