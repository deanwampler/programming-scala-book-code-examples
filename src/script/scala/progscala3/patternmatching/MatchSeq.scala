// tag::seqToString[]
// src/script/scala/progscala3/patternmatching/MatchSeq.scala

def seqToString[T](seq: Seq[T]): String = seq match                  // <1>
  case head +: tail => s"($head +: ${seqToString(tail)})"            // <2>
  case Nil => "Nil"                                                  // <3>
// end::seqToString[]

seqToString(Seq(1, 2, 3)
seqToString(Seq.empty[Int]
seqToString(Vector(1, 2, 3)
seqToString(Vector.empty[Int]
seqToString(Map("one" -> 1, "two" -> 2, "three" -> 3)
seqToString(Map.empty[String,Int]


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
