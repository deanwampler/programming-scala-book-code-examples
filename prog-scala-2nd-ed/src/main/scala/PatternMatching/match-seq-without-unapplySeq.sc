// src/main/scala/PatternMatching/match-seq-unapplySeq.sc

val nonEmptySeq    = Seq(1, 2, 3, 4, 5)
val emptySeq       = Seq()
val nonEmptyList   = List(1, 2, 3, 4, 5)
val emptyList      = List()
val nonEmptyVector = Vector(1, 2, 3, 4, 5)
val emptyVector    = Vector()
val nonEmptyMap    = Map("one" -> 1, "two" -> 2, "three" -> 3, "four" -> 4)
val emptyMap       = Map.empty

// Process pairs
def processSeqPairs[T](l: Seq[T]): Unit = l match {
  case head1 +: head2 +: tail => 
    printf("(%s, %s), ", head1, head2)
    processSeqPairs(head2 +: tail)
  case head +: tail => 
    printf("(%s, _), ", head)
    processSeqPairs(tail)
  case Nil => print("Nil")
}

for (l <- Seq(
    nonEmptySeq, emptySeq, 
    nonEmptyList, emptyList, 
    nonEmptyVector, emptyVector, 
    nonEmptyMap.toSeq, emptyMap.toSeq)) {
  print("Seq: ")
  processSeqPairs(l)
  println()
}
