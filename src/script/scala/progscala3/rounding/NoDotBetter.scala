// src/script/scala/progscala3/rounding/NoDotBetter.scala

def isEven(n: Int) = (n % 2) == 0

List(1, 2, 3, 4) filter isEven foreach println
