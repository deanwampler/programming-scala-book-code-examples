// src/script/scala/progscala3/forcomps/ForGuard.scala

val states = Vector("Alabama", "Alaska", "Virginia", "Wyoming")

val results1 = for
  s <- states
  c <- s
  if c.isLower
yield s"$c-${c.toUpper}"

val results2 = states.
  flatMap(s => s.toSeq).
  withFilter(c => c.isLower).
  map(c => s"$c-${c.toUpper}")
