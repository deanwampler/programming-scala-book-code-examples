// src/main/scala/progscala3/fp/datastructs/seq.sc

val seq1 = Seq("Programming", "Scala")                               // <1>
val seq2 = "People" +: "should" +: "read" +: seq1                    // <2>
assert(seq2 == 
  List("People", "should", "read", "Programming", "Scala"))

val seq3 = "Programming" +: "Scala" +: Seq.empty                     // <3>
val seq4 = "People" +: "should" +: "read" +: Seq.empty
val seq5 = seq4 ++ seq3                                              // <4>
assert(seq5 == 
  List("People", "should", "read", "Programming", "Scala"))
