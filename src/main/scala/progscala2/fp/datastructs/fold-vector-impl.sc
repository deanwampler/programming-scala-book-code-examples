// src/main/scala/progscala2/fp/datastructs/fold-vector-impl.sc

// Specific implementation for Vectors where we append new elements,
// not prepend them like we did in fold-impl.sc for Seqs.
def reduceLeftV[A,B](s: Vector[A])(f: A => B): Vector[B] = {
  @annotation.tailrec
  def rl(accum: Vector[B], s2: Vector[A]): Vector[B] = s2 match {
    case head +: tail => rl(accum :+ f(head), tail)
    case _ => accum
  }
  rl(Vector.empty[B], s)
}

def reduceRightV[A,B](s: Vector[A])(f: A => B): Vector[B] = s match {
  case head +: tail => reduceRightV(tail)(f) :+ f(head)
  case _ => Vector.empty[B]
}

val vect = Vector(1,2,3,4,5,6)

reduceLeftV(vect)(i => 2*i)
// => Vector(2, 4, 6, 8, 10, 12)

reduceRightV(vect)(i => 2*i)
// => Vector(12, 10, 8, 6, 4, 2)