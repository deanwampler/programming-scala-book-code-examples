// src/test/scala/progscala3/forcomps/ForOptionsGoodSuite.scala
package progscala3.forcomps

import munit.*

class ForOptionsGoodSuite extends FunSuite:
  def positive(i: Int): Option[Int] =
    if i > 0 then Some(i) else None

  test("for comprehensions handle options intuitively") {
    val result = for
      i1 <- positive(5)
      i2 <- positive(10 * i1)
      i3 <- positive(25 * i2)
      i4 <- positive(2  * i3)
    yield (i1 + i2 + i3 + i4)
    assert(result == Some(3805))
  }

  test("for comprehension steps stop when None is found") {
    val result = for
      i1 <- positive(5)
      i2 <- positive(-1 * i1)              // <1>   EPIC FAIL!
      i3 <- positive(25 * i2)              // <2>
      i4 <- positive(-2 * i3)              // EPIC FAIL!
    yield (i1 + i2 + i3 + i4)
    assert(result == None)
  }
