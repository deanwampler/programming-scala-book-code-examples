// src/main/scala/progscala3/concurrency/direct/BoundaryExamples.scala
package progscala3.concurrency.boundary

import scala.util.boundary, boundary.break
import progscala3.concurrency.boundary.optional.*

object BoundaryExamples:
  def firstIndex[T](xs: Seq[T], elem: T): Int =
    boundary:
      for (x, i) <- xs.zipWithIndex do
        if x == elem then break(i)
      -1

  def firstTwoIndices[T](xs: Seq[T], elem1: T, elem2: T): (Int, Int) =
    boundary:
      firstIndex(xs, elem1) match
        case -1 => break((-1, -1))
        case i  => break((i, firstIndex(xs, elem2)))

  /**
   * For a sequence (rows) of sequences (columns) return a sequence with just
   * the first column (i.e., the first element in each row). Because a row might
   * be empty, wrap the returned value in an Option. If any row is empty, return None
   * for the whole thing.
   * An example using "optional". See comments in `optional.scala` for details.
   */
  def firstColumn[T](xss: Seq[Seq[T]]): Option[Seq[T]] =
    optional:
      xss.map(_.headOption.?)
   
  def main(args: Array[String]) =
    val xs = (0 until 10).toSeq
    assert(BoundaryExamples.firstIndex(xs,  0) == 0)
    assert(BoundaryExamples.firstIndex(xs,  9) == 9)
    assert(BoundaryExamples.firstIndex(xs, -1) == -1)
    assert(BoundaryExamples.firstIndex(xs, 10) == -1)
    assert(BoundaryExamples.firstTwoIndices(xs,  0, 9) == (0, 9))
    assert(BoundaryExamples.firstTwoIndices(xs,  9, 0) == (9, 0))
    assert(BoundaryExamples.firstTwoIndices(xs, -1, 0) == (-1, -1))
    assert(BoundaryExamples.firstTwoIndices(xs, 10, 0) == (-1, -1))
    assert(BoundaryExamples.firstTwoIndices(xs, 0, -1) == (0, -1))
    assert(BoundaryExamples.firstTwoIndices(xs, 0, 10) == (0, -1))

    val xssSome = List(List(0), List(1,0), List(2,1,0), List(3,2,1,0))
    val xssNone = List(List(0), Nil, List(2,1,0), List(3,2,1,0))
    assert(firstColumn(xssSome) == Some(List(0,1,2,3)))
    assert(firstColumn(xssNone) == None)
