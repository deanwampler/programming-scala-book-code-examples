// src/script/scala/progscala3/typelessdomore/FibonacciTailrec.scala
import scala.annotation.tailrec

@tailrec
def fibonacci(i: Int): BigInt =
  if i <= 1 then BigInt(1)
  else fibonacci(i - 2) + fibonacci(i - 1)     // Error with @tailrec

(0 to 5).foreach(i => println(s"$i: ${fibonacci(i)}"))
