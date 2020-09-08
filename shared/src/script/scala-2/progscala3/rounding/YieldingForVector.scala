// src/script/scala-2/progscala3/rounding/YieldingForVector.scala

val odds = for {
  number <- Vector(1,2,3,4,5)
  if number % 2 == 1
} yield number.toString

assert(odds == Vector("1", "3", "5"))
