// src/script/scala/progscala3/fp/datastructs/Reduce.scala

val int1 = Seq(1,2,3,4,5,6).reduceLeft (_ + _)             // <1>

val int2 = Seq(1,2,3,4,5,6).foldLeft(0)(_ + _)             // <2>

val int3 = Seq.empty[Int].reduceLeft(_ + _)                // <3>

val int4 = Seq(1).reduceLeft(_ + _)                        // <4>

val opt1 = Seq.empty[Int].reduceLeftOption(_ + _)          // <5>

val opt2 = Seq(1,2,3,4,5,6).reduceLeftOption(_ * _)
