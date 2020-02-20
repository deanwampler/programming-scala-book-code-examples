// src/test/scala/progscala3/forcomps/ForEithersSeqSuite.scala
package progscala3.forcomps

import munit._
import progscala3.metaprogramming.requirement
import scala.util.{ Either, Left, Right }

object ForEithersSeqSuite extends FunSuite {

  test("For loops skip Lefts") {
		val seq: Seq[Either[RuntimeException,Int]] =
		  Vector(Right(10), Left(new RuntimeException("boo!")), Right(20))

		val results2 = for {
		  case Right(i) <- seq
		} yield (2 * i)
		requirement(results2 == Vector(20, 40))
	}
}
