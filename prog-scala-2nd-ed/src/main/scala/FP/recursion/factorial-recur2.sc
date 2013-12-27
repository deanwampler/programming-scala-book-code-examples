// src/main/scala/FP/recursion/factorial-recur2.sc

import scala.annotation.tailrec

def factorial(i: BigInt): BigInt = {
  @tailrec
  def fact(i: BigInt, accumulator: BigInt): BigInt = i match {
    case _ if i == 1 => accumulator
    case _ => fact(i - 1, i * accumulator)
  }
  fact(i, 1)
}

for (i <- 1 to 10)
  println(s"$i: ${factorial(i)}")
