// src/script/scala/progscala3/patternmatching/MatchTypes.scala

val results = Seq(Seq(5.5,5.6,5.7), Seq("a", "b")) map {
  case seqd: Seq[Double] => ("seq double", seqd)
  case seqs: Seq[String] => ("seq string", seqs)
  case other             => ("unknown!", other)
}
