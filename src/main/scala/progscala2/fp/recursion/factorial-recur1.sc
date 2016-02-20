// src/main/scala/progscala2/fp/recursion/factorial-recur1.sc
import scala.annotation.tailrec

// What happens if you uncomment the annotation??
// @tailrec
def factorial(i: BigInt): BigInt = {
  if (i == 1) i
  else i * factorial(i - 1)
}

for (i <- 1 to 10)
  println(s"$i:\t${factorial(i)}")
