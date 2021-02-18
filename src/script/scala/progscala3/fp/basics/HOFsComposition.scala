// src/script/scala/progscala3/fp/basics/HOFsComposition.scala

val result = (1 to 10).filter(_ % 2 == 0).map(_ * 2).reduce(_ * _)
assert(result == 122880)
