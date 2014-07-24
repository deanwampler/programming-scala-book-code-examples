// src/main/scala/Collections/safe-seq.sc

val mutableSeq1: Seq[Int] = List(1,2,3,4)
val mutableSeq2: Seq[Int] = Array(1,2,3,4)

import safeseq._

val immutableSeq1: Seq[Int] = List(1,2,3,4)
val immutableSeq2: Seq[Int] = Array(1,2,3,4)

