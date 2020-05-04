// src/script/scala/progscala3/forcomps/ForTriesGood.scala
import scala.util.{ Try, Success, Failure }

def positive(i: Int): Try[Int] = Try {
  assert (i > 0, s"nonpositive number $i")
  i
}

val result = for
  i1 <- positive(5)
  i2 <- positive(10 * i1)
  i3 <- positive(25 * i2)
  i4 <- positive(2  * i3)
yield (i1 + i2 + i3 + i4)

val result2 = for
  i1 <- positive(5)
  i2 <- positive(-1 * i1)      // FAIL!
  i3 <- positive(25 * i2)
  i4 <- positive(-2 * i3)
yield (i1 + i2 + i3 + i4)
