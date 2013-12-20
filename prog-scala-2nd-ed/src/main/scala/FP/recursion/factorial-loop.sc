// code-examples/FP/recursion/factorial-loop-script.scala

def factorial_loop(i: BigInt): BigInt = {
  var result = BigInt(1)
  for (j <- 2 to i.intValue)
    result *= j
  result
}

for (i <- 1 to 10)
  format("%s: %s\n", i, factorial_loop(i))
