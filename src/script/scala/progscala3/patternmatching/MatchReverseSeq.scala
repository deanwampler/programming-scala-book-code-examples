// src/script/scala/progscala3/patternmatching/MatchReverseSeq.scala

def reverseSeqToString[T](l: Seq[T]): String = l match
  case prefix :+ end => s"(${reverseSeqToString(prefix)} :+ $end)"
  case Nil => "Nil"

val nonEmptySeq = Vector(1, 2, 3, 4, 5)

assert(reverseSeqToString(nonEmptySeq) ==
  "(((((Nil :+ 1) :+ 2) :+ 3) :+ 4) :+ 5)")

Vector.empty[Int] :+ 1 :+ 2 :+ 3 :+ 4 :+ 5
