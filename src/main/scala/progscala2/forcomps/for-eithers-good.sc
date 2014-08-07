// src/main/scala/progscala2/forcomps/for-eithers-good.sc

def positive(i: Int): Either[String,Int] = 
  if (i > 0) Right(i) else Left(s"nonpositive number $i")

for {
  i1 <- positive(5).right
  i2 <- positive(10 * i1).right
  i3 <- positive(25 * i2).right
  i4 <- positive(2  * i3).right
} yield (i1 + i2 + i3 + i4)
// Returns: scala.util.Either[String,Int] = Right(3805)

for {
  i1 <- positive(5).right
  i2 <- positive(-1 * i1).right   // EPIC FAIL!
  i3 <- positive(25 * i2).right
  i4 <- positive(-2 * i3).right   // EPIC FAIL!
} yield (i1 + i2 + i3 + i4)
// Returns: scala.util.Either[String,Int] = Left(nonpositive number -5)
