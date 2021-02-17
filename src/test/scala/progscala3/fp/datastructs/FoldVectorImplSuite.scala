// src/test/scala/progscala3/fp/datastructs/FoldVectorImplSuite.scala
package progscala3.fp.datastructs

import munit.*
import scala.annotation.tailrec

class FoldVectorImplSuite extends FunSuite:

  /**
   * Simple implementation of reduceLeft for transforming Vectors
   * to vectors.
   .*/
  def reduceLeftV[A,B](s: Vector[A])(f: A => B): Vector[B] =
    @tailrec
    def rl(accum: Vector[B], s2: Vector[A]): Vector[B] = s2 match
      case head +: tail => rl(accum :+ f(head), tail)
      case _ => accum
    rl(Vector.empty[B], s)

  /**
   * Simple implementation of reduceRight for transforming Vectors
   * to vectors.
   */
  def reduceRightV[A,B](s: Vector[A])(f: A => B): Vector[B] = s match
    case head +: tail => reduceRightV(tail)(f) :+ f(head)
    case _ => Vector.empty[B]

  val vect = Vector(1,2,3,4,5,6)

  test("reduceLeftV returns a new vector with the element order preserved") {
    assert(reduceLeftV(vect)(i => 2*i)  == Vector(2, 4, 6, 8, 10, 12))
  }

  test("reduceRightV returns a new vector with the element order reversed") {
    assert(reduceRightV(vect)(i => 2*i) == Vector(12, 10, 8, 6, 4, 2))
  }
