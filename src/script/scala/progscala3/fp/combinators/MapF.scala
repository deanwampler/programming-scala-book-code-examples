// src/script/scala/progscala3/fp/combinators/MapF.scala

object MapF:                                                    // <1>
  def map[A,B](f: (A) => B)(seq: Seq[A]): Seq[B] = seq.map(f)   // <2>

val intToString = (i:Int) => s"N=$i"
val input = Seq(1, 2, 3, 4)

val fl = MapF.map(intToString)
val seq = fl(input)
