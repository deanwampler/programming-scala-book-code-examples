// src/script/scala/progscala3/forcomps/ForForeach.scala

val states = Vector("Alabama", "Alaska", "Virginia", "Wyoming")

var lower1 = Vector.empty[String]
for
  s <- states
do lower1 = lower1 :+ s.toLowerCase

var lower2 = Vector.empty[String]
for s <- states do lower2 = lower2 :+ s.toLowerCase

var lower3 = Vector.empty[String]
states.foreach(s => lower3 = lower3 :+ s.toLowerCase)
