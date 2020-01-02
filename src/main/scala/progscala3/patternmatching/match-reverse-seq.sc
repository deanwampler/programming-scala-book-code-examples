// src/main/scala/progscala3/patternmatching/match-reverse-seq.sc
// Compare to match-seq.sc

val nonEmptyList   = List(1, 2, 3, 4, 5)
val nonEmptyVector = Vector(1, 2, 3, 4, 5)
val nonEmptyMap    = Map("one" -> 1, "two" -> 2, "three" -> 3)

def reverseSeqToString[T](l: Seq[T]): String = l match {
  case prefix :+ end => reverseSeqToString(prefix) + s" :+ $end"
  case Nil => "Nil"
}

val results = Seq(nonEmptyList, nonEmptyVector, nonEmptyMap.toSeq) map {
  seq => reverseSeqToString(seq)
}
assert(results == Seq(
  "Nil :+ 1 :+ 2 :+ 3 :+ 4 :+ 5", 
  "Nil :+ 1 :+ 2 :+ 3 :+ 4 :+ 5", 
  "Nil :+ (one,1) :+ (two,2) :+ (three,3)"))
