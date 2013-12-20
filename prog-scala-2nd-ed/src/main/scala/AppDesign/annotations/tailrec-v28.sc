// code-examples/AppDesign/annotations/tailrec-v28-script.scala
import scala.annotation.tailrec

@tailrec
def fib(i: Int): Int = i match {
    case _ if i <= 1 => i
    case _ => fib(i-1) + fib(i-2)
}
println(fib(5))
