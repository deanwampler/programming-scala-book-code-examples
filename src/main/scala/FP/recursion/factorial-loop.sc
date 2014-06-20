// src/main/scala/FP/recursion/factorial-loop.sc

def factorial_loop(i: BigInt): BigInt = {
  var result = BigInt(1)
  for (j <- 2 to i.intValue)
    result *= j
  result
}

for (i <- 1 to 10)
  println(s"$i: ${factorial(i)}")
