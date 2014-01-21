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
def processUnapplySeq[T](l: Seq[T]): Unit = l match {
  case Seq(head1, head2, _*) => 
    printf("(%s, %s), ", head1, head2)
    processUnapplySeq(l.tail)  // We didn't capture the "tail" in the match
  case Seq(head, _*) => 
    printf("(%s, _), ", head)
    processUnapplySeq(l.tail)
  case Nil => print("Nil")
}

for (l <- Seq(
    nonEmptySeq, emptySeq, 
    nonEmptyList, emptyList, 
    nonEmptyVector, emptyVector, 
    nonEmptyMap.toSeq, emptyMap.toSeq)) {
  print("Seq: ")
  processUnapplySeq(l)
  println()
}
