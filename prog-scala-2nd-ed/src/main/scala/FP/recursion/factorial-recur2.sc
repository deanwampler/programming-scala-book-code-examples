// code-examples/FP/recursion/factorial-recur2-script.scala

def factorial(i: BigInt): BigInt = {
  def fact(i: BigInt, accumulator: BigInt): BigInt = i match {
    case _ if i == 1 => accumulator
    case _ => fact(i - 1, i * accumulator)
  }
  fact(i, 1)
}

for (i <- 1 to 10)
  format("%s: %s\n", i, factorial(i))
