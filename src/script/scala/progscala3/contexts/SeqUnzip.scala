// src/script/scala/progscala3/contexts/SeqUnzip.scala
// Not discussed in the book.

val seq = (0 to 10).toList

object noimplicit:
  val unzipped = seq.unzip      // Error.

object topair:
  implicit val toPair: Int => (Int, String) = i => (i, (2*i).toString)
  def unzipped = seq.unzip     // Works, because of toPair.

topair.unzipped
