// src/script/scala/progscala3/forcomps/ForMap.scala

val states = Vector("Alabama", "Alaska", "Virginia", "Wyoming")

var upper1a = Vector.empty[String]
var upper1b = Vector.empty[String]
var upper2  = Vector.empty[String]

val upper1a = for
  s <- states
yield s.toUpperCase

val upper1b = for s <- states yield s.toUpperCase

val upper2 = states.map(_.toUpperCase)
