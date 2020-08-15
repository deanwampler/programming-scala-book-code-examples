// src/script/scala/progscala3/fp/combinators/Combinators.scala

object CombinatorsLF {                                          // <1>
  def map[A,B](list: Seq[A])(f: (A) => B): Seq[B] = list map f  // <2>
}

object CombinatorsFL {                                          // <3>
  def map[A,B](f: (A) => B)(list: Seq[A]): Seq[B] = list map f
}

val intToString = (i:Int) => s"N=$i"

val input = Seq(1, 2, 3, 4)
val expected = Seq[String]("N=1", "N=2", "N=3", "N=4")

val lf = CombinatorsLF.map(input)
val seq1 = lf(intToString)
assert(seq1 == expected)

val fl = CombinatorsFL.map(intToString)
val seq2 = fl(input)
assert(seq2 == expected)
