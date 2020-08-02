// src/script/scala/progscala3/metaprogramming/SummonAll.scala

import compiletime.summonAll

given as Int = 10                                               // <1>
given as String = "foo"
given as Double = 1.2
val tuple1 = summonAll[Int *: String *: Double *: EmptyTuple]   // <2>
assert(tuple1 == (10, "foo", 1.2))
