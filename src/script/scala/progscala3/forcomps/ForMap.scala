// src/script/scala/progscala3/forcomps/ForMap.scala

val states   = Vector("Alabama", "Alaska", "Virginia", "Wyoming")

val results1 = for
  s <- states
yield s.toUpperCase

val results2 = states map (_.toUpperCase)
