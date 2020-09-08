// src/script/scala/progscala3/forcomps/ForTries.scala

import scala.util.{ Try, Success, Failure }

def positiveTries(i: Int): Try[Int] = Try {
  assert (i > 0, s"nonpositive number $i")
  i
}

val result = for
  i1 <- positiveTries(5)
  i2 <- positiveTries(10 * i1)
  i3 <- positiveTries(25 * i2)
  i4 <- positiveTries(2  * i3)
yield (i1 + i2 + i3 + i4)

val result2 = for
  i1 <- positiveTries(5)
  i2 <- positiveTries(-1 * i1)      // FAIL!
  i3 <- positiveTries(25 * i2)
  i4 <- positiveTries(-2 * i3)
yield (i1 + i2 + i3 + i4)
