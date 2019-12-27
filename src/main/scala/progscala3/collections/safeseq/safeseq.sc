// src/main/scala/progscala3/collections/safeseq/safeseq.sc

val mutableSeq1: Seq[Int] = List(1,2,3,4)
val mutableSeq2: Seq[Int] = Array(1,2,3,4)
println(s"mutable: $mutableSeq1, $mutableSeq2")

import progscala3.collections.safeseq._

val immutableSeq1: Seq[Int] = List(1,2,3,4)
val immutableSeq2: Seq[Int] = Array(1,2,3,4)
println(s"immutable: $immutableSeq1, $immutableSeq2")
