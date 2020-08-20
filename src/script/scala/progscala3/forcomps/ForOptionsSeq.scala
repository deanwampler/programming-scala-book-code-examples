// src/script/scala/progscala3/forcomps/ForOptionsSeq.scala

def positiveOption(i: Int): Option[Int] =
  if i > 0 then Some(i) else None

val resultSuccess = for
  i1 <- positiveOption(5)
  i2 <- positiveOption(10 * i1)
  i3 <- positiveOption(25 * i2)
  i4 <- positiveOption(2  * i3)
yield (i1 + i2 + i3 + i4)

val resultFail = for
  i1 <- positiveOption(5)
  i2 <- positiveOption(-1 * i1)    // <1>
  i3 <- positiveOption(25 * i2)
  i4 <- positiveOption(-2 * i3)
yield (i1 + i2 + i3 + i4)
