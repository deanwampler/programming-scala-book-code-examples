// src/main/scala/FP/datastructs/seq.sc

val seq1 = Seq("Programming", "Scala")
val seq2 = "People" +: "should" +: "read" +: seq1
println(seq2)

val seq3 = "Programming" +: "Scala" +: Seq.empty[String]
val seq4 = "People" +: "should" +: "read" +: Seq.empty[String]
val seq5 = seq4 ++ seq3
println(seq5)
