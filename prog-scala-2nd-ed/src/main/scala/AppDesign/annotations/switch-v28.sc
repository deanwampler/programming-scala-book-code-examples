// code-examples/AppDesign/annotations/switch-v28-script.scala
import scala.annotation.switch

def fib(i: Int): Int = (i: @switch) match {
    case _ if i <= 1 => i
    case _ => fib(i-1) + fib(i-2)
}
println(fib(5))
