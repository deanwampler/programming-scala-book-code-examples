// src/script/scala/progscala3/typelessdomore/FibonacciTailrec.scala
import scala.annotation.tailrec

@tailrec
def fibonacci(i: Int): Long =
  if (i <= 1) 1L
  else fibonacci(i - 2) + fibonacci(i - 1)     // Error with @tailrec

(0 to 5).foreach(i => println(s"$i: ${fibonacci(i)}"))