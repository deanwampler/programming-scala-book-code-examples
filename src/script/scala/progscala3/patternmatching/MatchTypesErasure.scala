// src/script/scala/progscala3/patternmatching/MatchTypesErasure.scala

val results = Seq(Seq(5.5,5.6,5.7), Seq("a", "b")).map {
  case seqd: Seq[Double] => ("seq double", seqd)   // Erasure warning
  case seqs: Seq[String] => ("seq string", seqs)   // Erasure warning
  case other             => ("unknown!", other)
}
