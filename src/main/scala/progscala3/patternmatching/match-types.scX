// src/main/scala/progscala3/patternmatching/match-types.scX
// Does not compile with strict warnings, because of type erasure,
// the compiler can't distinguish between Seq[X] and Seq[Y], as
// written.

val results = Seq(Seq(5.5,5.6,5.7), Seq("a", "b")) map {
  case seqd: Seq[Double] => ("seq double", seqd)
  case seqs: Seq[String] => ("seq string", seqs)
  case _                 => ("unknown!", x)
}

