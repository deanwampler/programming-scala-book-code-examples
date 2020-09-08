// src/script/scala/progscala3/forcomps/ForEithers.scala

def positiveEither(i: Int): Either[String,Int] =
  if i > 0 then Right(i) else Left(s"nonpositive number $i")

// If each step returns Right, the result is a Right
val result1 = for
  i1 <- positiveEither(5)
  i2 <- positiveEither(10 * i1)
  i3 <- positiveEither(25 * i2)
  i4 <- positiveEither(2  * i3)
yield (i1 + i2 + i3 + i4)

// If any step returns Left, the result is a Left
val result2 = for
  i1 <- positiveEither(5)
  i2 <- positiveEither(-1 * i1)   // <1>
  i3 <- positiveEither(25 * i2)
  i4 <- positiveEither(-2 * i3)
yield (i1 + i2 + i3 + i4)
