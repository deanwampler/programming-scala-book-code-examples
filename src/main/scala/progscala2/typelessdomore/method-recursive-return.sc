// src/main/scala/progscala2/typelessdomore/method-recursive-return.sc
// ERROR: Won't compile until you put an Int return type on "fact".
import scala.annotation.tailrec

def factorial(i: Int) = {
  @tailrec
  def fact(i: Int, accumulator: Int) = {
    if (i <= 1)
      accumulator
    else
      fact(i - 1, i * accumulator)  // COMPILATION ERROR
  }

  fact(i, 1)
}

