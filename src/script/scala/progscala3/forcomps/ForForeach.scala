// src/script/scala/progscala3/forcomps/ForForeach.scala

val states   = Vector("Alabama", "Alaska", "Virginia", "Wyoming")

var lower1a = Vector.empty[String]
var lower1b = Vector.empty[String]
var lower2  = Vector.empty[String]

for
  s <- states
do lower1a = lower1 :+ s.toLowerCase

for s <- states do lower1b = lower1 :+ s.toLowerCase

states.foreach(s => lower2 = lower2 :+ s.toLowerCase)
