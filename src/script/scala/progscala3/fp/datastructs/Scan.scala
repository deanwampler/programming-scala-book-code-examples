// src/script/scala/progscala3/fp/datastructs/Scan.scala

val ints = Seq(1,2,3,4,5,6)

val plus = ints.scan(0)(_ + _)
val mult = ints.scan(1)(_ * _)
