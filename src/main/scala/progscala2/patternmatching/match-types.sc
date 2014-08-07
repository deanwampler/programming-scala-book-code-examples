// src/main/scala/progscala2/patternmatching/match-types.sc

for {
  x <- Seq(List(5.5,5.6,5.7), List("a", "b")) 
} yield (x match {
  case seqd: Seq[Double] => ("seq double", seqd)
  case seqs: Seq[String] => ("seq string", seqs)
  case _                 => ("unknown!", x)
})
