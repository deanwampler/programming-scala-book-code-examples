// src/main/scala/progscala3/forcomps/for-tries-good.sc
import scala.util.{ Try, Success, Failure }

def positive(i: Int): Try[Int] = Try {
  assert (i > 0, s"nonpositive number $i")
  i
}

val result1 = for {
  i1 <- positive(5)
  i2 <- positive(10 * i1)
  i3 <- positive(25 * i2)
  i4 <- positive(2  * i3)
} yield (i1 + i2 + i3 + i4)
assert(result1 == Success(3805))

val result2 = for {
  i1 <- positive(5)
  i2 <- positive(-1 * i1)              // EPIC FAIL!
  i3 <- positive(25 * i2)
  i4 <- positive(-2 * i3)              // EPIC FAIL!
} yield (i1 + i2 + i3 + i4)

result2 match {
  case Failure(assertionerror) => 
    assert(assertionerror.getMessage.contains("nonpositive number -5"))
  case Success(_) => 
    assert(false, "succeeded when it should have failed!")
}
