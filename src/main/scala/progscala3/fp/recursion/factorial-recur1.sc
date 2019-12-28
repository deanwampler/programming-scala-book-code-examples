// src/main/scala/progscala3/fp/recursion/factorial-recur1.sc

// If you uncomment the following annotation, you get:
//   error: could not optimize @tailrec annotated method factorial: 
//   it contains a recursive call not in tail position
// @scala.annotation.tailrec
def factorial(i: BigInt): BigInt = {
  if (i == 1) i
  else i * factorial(i - 1)
}

for (i <- 1 to 10)
  println(s"$i:\t${factorial(i)}")
