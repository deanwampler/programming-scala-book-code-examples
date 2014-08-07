// src/main/scala/progscala2/patternmatching/match-seq-unapplySeq.sc

val nonEmptyList   = List(1, 2, 3, 4, 5)                             // <1>
val emptyList      = Nil
val nonEmptyMap    = Map("one" -> 1, "two" -> 2, "three" -> 3)

// Process pairs
def windows[T](seq: Seq[T]): String = seq match {
  case Seq(head1, head2, _*) =>                                      // <2>
    s"($head1, $head2), " + windows(seq.tail)                        // <3>
  case Seq(head, _*) => 
    s"($head, _), " + windows(seq.tail)                              // <4>
  case Nil => "Nil"
}

for (seq <- Seq(nonEmptyList, emptyList, nonEmptyMap.toSeq)) {
  println(windows(seq))
}
