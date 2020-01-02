// src/main/scala/progscala3/implicits/implicitly-args.sc
import math.Ordering

case class MySeq[A](seq: Seq[A]) {
  def sortBy1[B](f: A => B)(implicit ord: Ordering[B]): Seq[A] =
    seq.sortBy(f)(ord)

  def sortBy2[B : Ordering](f: A => B): Seq[A] =
    seq.sortBy(f)(implicitly[Ordering[B]])
}

val seq = MySeq(Seq(1,3,5,2,4))

assert(seq.sortBy1(i => -i) == Seq(5, 4, 3, 2, 1))
assert(seq.sortBy2(i => -i) == Seq(5, 4, 3, 2, 1))
