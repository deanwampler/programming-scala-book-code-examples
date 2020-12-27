// src/script/scala/progscala3/typelessdomore/MethodRecursiveReturn.scala
// ERROR: Won't compile until you put an Int return type on "fact".
import scala.annotation.tailrec

def factorial(i: Int) =
  @tailrec
  def fact(i: Int, accumulator: Int) =
    if i <= 1 then accumulator
    else fact(i - 1, i * accumulator)  // COMPILATION ERROR

  fact(i, 1)
