// src/test/scala/progscala3/forcomps/ForTriesGoodSuite.scala
package progscala3.forcomps
import scala.util.{Try, Success, Failure}
import munit.*

class ForTriesGoodSuite extends FunSuite:
  def positive(i: Int): Try[Int] = Try {
    assert (i > 0, s"nonpositive number $i")
    i
  }

  test("for comprehensions handle Try/Success values intuitively") {
    val result = for
      i1 <- positive(5)
      i2 <- positive(10 * i1)
      i3 <- positive(25 * i2)
      i4 <- positive(2  * i3)
    yield (i1 + i2 + i3 + i4)
    assert(result == Success(3805))
  }

  test("for comprehension steps stop when Try/Failure is found") {
    val result = for
      i1 <- positive(5)
      i2 <- positive(-1 * i1)              // EPIC FAIL!
      i3 <- positive(25 * i2)
      i4 <- positive(-2 * i3)              // EPIC FAIL!
    yield (i1 + i2 + i3 + i4)

    result match {
      case Failure(ex) =>
        assert(ex.getMessage.contains("nonpositive number -5"))
      case Success(_) =>
        assert(false, "succeeded when it should have failed!")
    }
  }
