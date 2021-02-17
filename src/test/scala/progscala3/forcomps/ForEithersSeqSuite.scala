// src/test/scala/progscala3/forcomps/ForEithersSeqSuite.scala
package progscala3.forcomps

import munit.*
import scala.util.{Either, Left, Right}

class ForEithersSeqSuite extends FunSuite:
  test("For loops skip Lefts") {
    val seq: Seq[Either[RuntimeException,Int]] =
      Vector(Right(10), Left(RuntimeException("boo!")), Right(20))

    val results2 = for
      case Right(i) <- seq
    yield (2 * i)
    assert(results2 == Vector(20, 40))
  }
