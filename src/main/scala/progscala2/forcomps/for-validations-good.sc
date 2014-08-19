// src/main/scala/progscala2/forcomps/for-validations-good.sc
import scalaz._, std.AllInstances._

def positive(i: Int): Validation[List[String], Int] = {
  if (i > 0) Success(i)                                              // <1>
  else Failure(List(s"Nonpositive integer $i"))
}

for {
  i1 <- positive(5)
  i2 <- positive(10 * i1)
  i3 <- positive(25 * i2)
  i4 <- positive(2  * i3)
} yield (i1 + i2 + i3 + i4)
// Returns: scalaz.Validation[List[String],Int] = Success(3805)

for {
  i1 <- positive(5)
  i2 <- positive(-1 * i1)              // EPIC FAIL!
  i3 <- positive(25 * i2)
  i4 <- positive(-2 * i3)              // EPIC FAIL!
} yield (i1 + i2 + i3 + i4)
// Returns: scalaz.Validation[List[String],Int] =
//   Failure(List(Nonpositive integer -5))                           // <2>

positive(5) +++ positive(10) +++ positive(25)                        // <3>
// Returns: scalaz.Validation[String,Int] = Success(40)

positive(5) +++ positive(-10) +++ positive(25) +++ positive(-30)     // <4>
// Returns: scalaz.Validation[String,Int] =
//   Failure(Nonpositive integer -10, Nonpositive integer -30)
