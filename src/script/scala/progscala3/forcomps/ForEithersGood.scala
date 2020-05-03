// src/script/scala/progscala3/forcomps/ForEithersGood.scala

def positive(i: Int): Either[String,Int] =
  if i > 0 then Right(i) else Left(s"nonpositive number $i")

// If each step returns Right, the result is a Right
val result1 = for
  i1 <- positive(5)
  i2 <- positive(10 * i1)
  i3 <- positive(25 * i2)
  i4 <- positive(2  * i3)
yield (i1 + i2 + i3 + i4)

// If any step returns Left, the result is a Left
val result2 = for
  i1 <- positive(5)
  i2 <- positive(-1 * i1)   // EPIC FAIL!
  i3 <- positive(25 * i2)
  i4 <- positive(-2 * i3)   // EPIC FAIL!
yield (i1 + i2 + i3 + i4)
