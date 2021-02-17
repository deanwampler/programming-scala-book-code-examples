// src/test/scala/progscala3/forcomps/ForTriesSeqSuite.scala
package progscala3.forcomps

import scala.util.{Try, Success, Failure}
import munit.*

class ForTriesSeqSuite extends FunSuite:
  val tries: Seq[Try[Int]] =
    Vector(Success(10), Failure(new RuntimeException("boo!")), Success(20))

  test("for comprehension over a sequence of Try objects removes Failures") {
    val ints = for
      case Success(i) <- tries
    yield (2 * i)
    assert(ints == Vector(20, 40))
  }
