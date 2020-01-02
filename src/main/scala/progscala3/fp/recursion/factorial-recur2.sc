// src/main/scala/progscala3/fp/recursion/factorial-recur2.sc
import scala.annotation.tailrec

def factorial(i: BigInt): BigInt = {
  @tailrec
  def fact(i: BigInt, accumulator: BigInt): BigInt =
    if (i == 1) accumulator
    else fact(i - 1, i * accumulator)

  fact(i, 1)
}

val facts5 = (1 to 5).map(i => (i, factorial(i)))
assert(facts5 == Seq(1 -> 1, 2 -> 2, 3 -> 6, 4 -> 24, 5 -> 120))
