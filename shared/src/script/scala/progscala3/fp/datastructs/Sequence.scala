// src/script/scala/progscala3/fp/datastructs/Sequence.scala

val seq1 = Seq("Programming", "Scala")             // <1>
val seq2 = "People" +: "should" +: "read" +: seq1  // <2>

seq2.head                                          // <3>
seq2.tail
