// src/test/scala/progscala3/fp/datastructs/FoldRightLeftSuite.scala
package progscala3.fp.datastructs

import munit._
import scala.annotation.tailrec

/**
 * Simplified implementations of foldLeft and foldRight.
 */
class FoldRightLeftSuite extends FunSuite:

	def foldLeft[A,B](seed: B)(s: Seq[A])(f: (B, A) => B): B =
	  @tailrec
	  def fl(accum: B, s2: Seq[A]): B = s2 match
	    case head +: tail => fl(f(accum, head), tail)
	    case _ => accum
	  fl(seed, s)

	def foldRight[A,B](seed: B)(s: Seq[A])(f: (A,B) => B): B =
		s match
		  case head +: tail => f(head, foldRight(seed)(tail)(f))
		  case _ => seed

	val list = List(1,2,3,4,5,6)

	test("foldLeft goes left to right") {
		assert(foldLeft("()")(list)((accum, i) => s"($accum $i)") ==
			"((((((() 1) 2) 3) 4) 5) 6)")
	}
	test("foldRight goes right to left") {
		assert(foldRight("()")(list)((i, accum) => s"($i $accum)") ==
			"(1 (2 (3 (4 (5 (6 ()))))))")
	}
