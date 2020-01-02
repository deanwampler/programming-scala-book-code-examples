// src/main/scala/progscala3/patternmatching/match-seq-parens.sc

val nonEmptySeq    = Seq(1, 2, 3, 4, 5)
val emptySeq       = Seq.empty[Int]
val nonEmptyMap    = Map("one" -> 1, "two" -> 2, "three" -> 3)

def seqToString2[T](seq: Seq[T]): String = seq match {
  case head +: tail => s"($head +: ${seqToString2(tail)})"           // <1>
  case Nil => "(Nil)"
}

val results = Seq(nonEmptySeq, emptySeq, nonEmptyMap.toSeq) map {
  seq => seqToString2(seq)
}
assert(results == Seq(
  "(1 +: (2 +: (3 +: (4 +: (5 +: (Nil))))))",
  "(Nil)",
  "((one,1) +: ((two,2) +: ((three,3) +: (Nil))))"))
