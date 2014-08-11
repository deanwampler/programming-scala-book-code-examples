// src/main/scala/progscala2/fp/datastructs/seq.sc

val seq1 = Seq("Programming", "Scala")                               // <1>
val seq2 = "People" +: "should" +: "read" +: seq1                    // <2>

val seq3 = "Programming" +: "Scala" +: Seq.empty                     // <3>
val seq4 = "People" +: "should" +: "read" +: Seq.empty
val seq5 = seq4 ++ seq3                                              // <4>
