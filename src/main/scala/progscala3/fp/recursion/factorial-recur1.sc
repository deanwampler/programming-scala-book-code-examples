// src/main/scala/progscala3/fp/recursion/factorial-recur1.sc

// If you uncomment the following annotation, you get:
//   error: could not optimize @tailrec annotated method factorial: 
//   it contains a recursive call not in tail position
// @scala.annotation.tailrec
def factorial(i: BigInt): BigInt = {
  if (i == 1) i
  else i * factorial(i - 1)
}

val facts5 = (1 to 5).map(i => (i, factorial(i)))
assert(facts5 == Seq(1 -> 1, 2 -> 2, 3 -> 6, 4 -> 24, 5 -> 120))
