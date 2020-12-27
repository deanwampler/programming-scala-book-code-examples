// src/script/scala/progscala3/fp/datastructs/FlatMap.scala
val seq = 0 until 5

val seq1a = seq.map(i => i until 5)
val seq1  = seq1a.flatten

val seq2  = seq.flatMap(i => i until 5)
