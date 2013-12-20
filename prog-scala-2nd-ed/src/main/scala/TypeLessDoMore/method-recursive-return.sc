// code-examples/TypeLessDoMore/method-recursive-return-script.scala
// ERROR: Won't compile until you put an Int return type on "fact".

def factorial(i: Int) = {
  def fact(i: Int, accumulator: Int) = {
    if (i <= 1)
      accumulator
    else
      fact(i - 1, i * accumulator)  // ERROR
  }
  
  fact(i, 1)
}

