// src/script/scala/progscala3/fp/basics/HOFsClosures2.scala

var factor2 = 2
def multiplier2(i: Int) = i * factor2

val result3 =
  (1 to 10).filter(_ % 2 == 0).map(multiplier2).reduce(_ * _)
assert(result3 == 122880)

factor2 = 3
val result4 =
  (1 to 10).filter(_ % 2 == 0).map(multiplier2).reduce(_ * _)
assert(result4 == 933120)
