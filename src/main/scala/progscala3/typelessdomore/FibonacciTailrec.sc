// src/main/scala/progscala2/typelessdomore/fibonacci-tailrec.scX
import scala.annotation.tailrec

@tailrec
def fibonacci(i: Int): Long = {
  if (i <= 1) 1L
  else fibonacci(i - 2) + fibonacci(i - 1)
}

(0 to 5) foreach ( i => println(fibonacci(i)) )
