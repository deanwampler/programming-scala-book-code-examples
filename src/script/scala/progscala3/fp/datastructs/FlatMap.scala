// src/script/scala/progscala3/fp/datastructs/FlatMap.scala
val seq = 0 until 5

val seq1 = seq.map(i => i until 5)

val seq2 = seq1.flatten

val seq3 = seq.flatMap(i => i until 5)
