// src/script/scala/progscala3/rounding/DoWhileAlternative.scala

var count = 0
while
  count += 1
  println(count)
  count < 10
do {}
assert(count == 10)
