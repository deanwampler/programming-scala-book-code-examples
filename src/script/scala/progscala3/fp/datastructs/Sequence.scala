// src/script/scala/progscala3/fp/datastructs/Sequence.scala

val seq1 = Seq("Programming", "Scala")             // <1>
val seq2 = "Programming" +: "Scala" +: Nil         // <2>
val seq3 = "People" +: "should" +: "read" +: seq1  // <3>

seq3.head                                          // <4>
seq3.tail
