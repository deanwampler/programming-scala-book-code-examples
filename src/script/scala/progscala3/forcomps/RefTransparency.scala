// src/script/scala/progscala3/forcomps/RefTransparency.scala

import RefTransparency._

for
  i <- 1 to 3
  j <- 1 to i
do println(s"$i, $j: ${addInts(i.toString, j.toString)}")

val add12 = addInts2("1", "2") == Right(3)

val add1x = addInts2("1", "x")
val addx2 = addInts2("x", "2")
val addxy = addInts2("x", "y")
