// src/script/scala/progscala3/patternmatching/AssignmentsFragile.scala

val h4a +: h4b +: t4 = Seq(1,2,3,4) : @unchecked
val h2a +: h2b +: t2 = Seq(1,2) : @unchecked
val h1a +: h1b +: t1 = Seq(1) : @unchecked       // MatchError!
