// src/script/scala/progscala3/patternmatching/MatchSeq.scala

val nonEmptySeq    = Seq(1, 2, 3)                                    // <1>
val emptySeq       = Seq.empty[Int]
val nonEmptyVector = Vector(1, 2, 3)                                 // <2>
val emptyVector    = Vector.empty[Int]
val nonEmptyMap    = Map("one" -> 1, "two" -> 2, "three" -> 3)       // <3>
val emptyMap       = Map.empty[String,Int]

def seqToString[T](seq: Seq[T]): String = seq match                  // <4>
  case head +: tail => s"($head +: ${seqToString(tail)})"            // <5>
  case Nil => "Nil"                                                  // <6>

val results = Seq(                                                   // <7>
    nonEmptySeq, emptySeq, nonEmptyVector, emptyVector,
    nonEmptyMap.toSeq, emptyMap.toSeq) map {
  seq => seqToString(seq)
}

assert(results == Seq(
  "(1 +: (2 +: (3 +: Nil)))",
  "Nil",
  "(1 +: (2 +: (3 +: Nil)))",
  "Nil",
  "((one,1) +: ((two,2) +: ((three,3) +: Nil)))",
  "Nil"))
