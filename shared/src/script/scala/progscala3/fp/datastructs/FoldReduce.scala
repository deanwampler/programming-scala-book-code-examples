// src/script/scala/progscala3/fp/datastructs/FoldReduce.scala

val int1 = Seq(1,2,3,4,5,6).reduce (_ + _)            // <1>

val int2 = Seq(1,2,3,4,5,6).fold(0)(_ + _)            // <2>

val int3 = Seq.empty[Int].reduce(_ + _)               // <3>

val int4 = Seq(1).reduce(_ + _)                       // <4>

val opt1 = Seq.empty[Int].reduceOption(_ + _)         // <5>

val opt2 = Seq(1,2,3,4,5,6).reduceOption(_ * _)
