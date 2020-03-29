// src/script/scala/progscala3/rounding/While.scala

def isThirteen(i: Int): Boolean = i == 13

var i = 0   // Normally you should avoid mutable variables!
while !isThirteen(i)
do
  println(s"$i isn't 13.")
  i += 1

assert(i == 13)
