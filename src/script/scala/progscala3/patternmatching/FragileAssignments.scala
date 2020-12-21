// src/script/scala/progscala3/patternmatching/FragileAssignments.scala

val h4a +: h4b +: t4 = Seq(1,2,3,4) // h4a = 1, h4b = 2, t4 = List(3,4)
val h2a +: h2b +: t2 = Seq(1,2)     // h2a = 1, h2b = 2, t2 = Nil
val h1a +: h1b +: t1 = Seq(1)       // MatchError!
