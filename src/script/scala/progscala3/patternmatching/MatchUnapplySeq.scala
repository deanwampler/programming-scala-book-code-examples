// src/script/scala/progscala3/patternmatching/MatchUnapplySeq.scala

// Process pairs
def windows[T](seq: Seq[T]): String = seq match
  case Seq(head1, head2, tail*) =>                                   // <1>
    s"($head1, $head2), " + windows(seq.tail)                        // <2>
  case Seq(head, tail*) =>                                           // <3>
    s"($head, _), " + windows(tail)
  case Nil => "Nil"                                                  // <4>

val nonEmptyList   = List(1, 2, 3, 4, 5)
val emptyList      = Nil
val nonEmptyMap    = Map("one" -> 1, "two" -> 2, "three" -> 3)

val results = Seq(nonEmptyList, emptyList, nonEmptyMap.toSeq).map {
  seq => windows(seq)
}
assert(results == Seq(
  "(1, 2), (2, 3), (3, 4), (4, 5), (5, _), Nil",
  "Nil",
  "((one,1), (two,2)), ((two,2), (three,3)), ((three,3), _), Nil"))
