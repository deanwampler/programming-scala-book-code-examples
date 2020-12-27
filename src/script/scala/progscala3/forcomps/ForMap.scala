// src/script/scala/progscala3/forcomps/ForMap.scala

val states = Vector("Alabama", "Alaska", "Virginia", "Wyoming")

val upper1 = for
  s <- states
yield s.toUpperCase

val upper2 = for s <- states yield s.toUpperCase

val upper3 = states.map(_.toUpperCase)
