// src/script/scala/progscala3/collections/GroupBy.scala

val ints = (1 until 100).toVector

val thirds = ints.groupBy(_%3)                                   // <1>
val thirds1a = thirds.view.mapValues(ns => ns.map(n => (n,2*n))) // <2>
val thirds1b = thirds1a.toMap                                    // <3>
val thirds2 = ints.groupMap(_%3)(n => (n,2*n))                   // <4>
