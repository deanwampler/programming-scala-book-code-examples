// src/main/scala/progscala3/forcomps/for-eithers-good.sc

def positive(i: Int): Either[String,Int] = 
  if (i > 0) Right(i) else Left(s"nonpositive number $i")

val result1 = for {
  i1 <- positive(5)
  i2 <- positive(10 * i1)
  i3 <- positive(25 * i2)
  i4 <- positive(2  * i3)
} yield (i1 + i2 + i3 + i4)
assert(result1 == Right(3805))

val result2 = for {
  i1 <- positive(5)
  i2 <- positive(-1 * i1)   // EPIC FAIL!
  i3 <- positive(25 * i2)
  i4 <- positive(-2 * i3)   // EPIC FAIL!
} yield (i1 + i2 + i3 + i4)
assert(result2 == Left("nonpositive number -5"))
