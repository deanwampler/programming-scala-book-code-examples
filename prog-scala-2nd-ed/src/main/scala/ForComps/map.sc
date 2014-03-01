// src/main/scala/ForComps/map.sc

val states = List("Alabama", "Alaska", "Virginia", "Wyoming")

for {
  s <- states
} yield s.toUpperCase

states map (_.toUpperCase)
