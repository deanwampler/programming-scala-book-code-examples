// src/main/scala/PatternMatching/match-reverse-seq.sc
// Compare to match-seq.sc

val nonEmptyList   = List(1, 2, 3, 4, 5)
val nonEmptyVector = Vector(1, 2, 3, 4, 5)
val nonEmptyMap    = Map("one" -> 1, "two" -> 2, "three" -> 3, "four" -> 4)

def processReverseSeq[T](l: Seq[T]): Unit = l match {
  case prefix :+ end => 
    processReverseSeq(prefix)
    printf(" :+ %s", end)
  case Nil => print("Nil")
}

for (l <- Seq(nonEmptyList, nonEmptyVector, nonEmptyMap.toSeq)) {
  print("Seq: ")
  processReverseSeq(l)
  println()
}
