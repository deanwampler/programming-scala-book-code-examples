// src/main/scala/ForComps/for-validates-good.sc

import scala.util.{ Try, Success, Failure }

def positive(i: Int): Try[Int] = Try {
  assert (i > 0)
  i
}

for {
  i1 <- positive(5)
  i2 <- positive(10 * i1)
  i3 <- positive(25 * i2)
} yield (i1 + i2 + i3)
// Returns: scala.util.Try[Int] = Success(1305)

for {
  i1 <- positive(5)
  i2 <- positive(-1 * i1)   // EPIC FAIL!
  i3 <- positive(25 * i2)
} yield (i1 + i2 + i3)
// Returns: scala.util.Try[Int] = Failure(
//   java.lang.AssertionError: assertion failed)
