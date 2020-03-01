// src/main/scala/progscala3/patternmatching/match-seq.sc

val nonEmptySeq    = Seq(1, 2, 3, 4, 5)                              // <1>
val emptySeq       = Seq.empty[Int]
val nonEmptyList   = List(1, 2, 3, 4, 5)                             // <2>
val emptyList      = Nil
val nonEmptyVector = Vector(1, 2, 3, 4, 5)                           // <3>
val emptyVector    = Vector.empty[Int]
val nonEmptyMap    = Map("one" -> 1, "two" -> 2, "three" -> 3)       // <4>
val emptyMap       = Map.empty[String,Int]

def seqToString[T](seq: Seq[T]): String = seq match {                // <5>
  case head +: tail => s"$head +: " + seqToString(tail)              // <6>
  case Nil => "Nil"                                                  // <7>
}

val results = Seq(                                                   // <8>
    nonEmptySeq, emptySeq, nonEmptyList, emptyList, 
    nonEmptyVector, emptyVector, nonEmptyMap.toSeq, emptyMap.toSeq) map {
  seq => seqToString(seq)
}

assert(results == Seq(
  "1 +: 2 +: 3 +: 4 +: 5 +: Nil",
  "Nil",
  "1 +: 2 +: 3 +: 4 +: 5 +: Nil",
  "Nil",
  "1 +: 2 +: 3 +: 4 +: 5 +: Nil",
  "Nil",
  "(one,1) +: (two,2) +: (three,3) +: Nil",
  "Nil"))
