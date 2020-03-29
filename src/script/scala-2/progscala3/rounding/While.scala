// src/script/scala-2/progscala3/rounding/While.scala

def isThirteen(i: Int): Boolean = {
  // Scala returns the result of the last expression in a method
  i == 13
}

var i = 0   // Normally you should avoid mutable variables!
while (!isThirteen(i)) {
  println(s"$i isn't 13.")
  i += 1
}
assert(i == 13)
