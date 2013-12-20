// code-examples/TypeLessDoMore/factorial-script.scala

def factorial(i: Int): Int = {
  def fact(i: Int, accumulator: Int): Int = {
    if (i <= 1)
      accumulator
    else
      fact(i - 1, i * accumulator)
  }
    
  fact(i, 1)
}

println( factorial(0) )
println( factorial(1) )
println( factorial(2) )
println( factorial(3) )
println( factorial(4) )
println( factorial(5) )
