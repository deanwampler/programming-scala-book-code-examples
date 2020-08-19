// src/script/scala/progscala3/forcomps/ForOptionsSeq.scala

def positive(i: Int): Option[Int] =
  if i > 0 then Some(i) else None

val resultSuccess = for
  i1 <- positive(5)
  i2 <- positive(10 * i1)
  i3 <- positive(25 * i2)
  i4 <- positive(2  * i3)
yield (i1 + i2 + i3 + i4)

val resultFail = for
  i1 <- positive(5)
  i2 <- positive(-1 * i1)    // <1>
  i3 <- positive(25 * i2)
  i4 <- positive(-2 * i3)
yield (i1 + i2 + i3 + i4)
