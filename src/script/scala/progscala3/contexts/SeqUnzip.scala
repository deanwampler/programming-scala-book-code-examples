// src/script/scala/progscala3/contexts/SeqUnzip.scala

val seq = (0 to 10).toList

object noimplicit {
  val unzipped = seq.unzip
}

object topair {
  implicit val toPair: Int => (Int, String) = i => (i, (2*i).toString)

  val unzipped = seq.unzip
}
topair.unzipped
