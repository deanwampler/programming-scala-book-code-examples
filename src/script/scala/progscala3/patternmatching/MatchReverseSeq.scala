// src/script/scala/progscala3/patternmatching/MatchReverseSeq.scala
// Compare to match-seq.sc

val nonEmptySeq = Seq(1, 2, 3, 4, 5)

def reverseSeqToString[T](l: Seq[T]): String = l match
  case prefix :+ end => s"(${reverseSeqToString(prefix)} :+ $end)"
  case Nil => "Nil"

assert(reverseSeqToString(nonEmptySeq) ==
	"(((((Nil :+ 1) :+ 2) :+ 3) :+ 4) :+ 5)")
