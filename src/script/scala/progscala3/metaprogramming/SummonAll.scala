// src/script/scala/progscala3/metaprogramming/SummonAll.scala

import compiletime.summonAll

given Int = 10                                                  // <1>
given String = "foo"
given Double = 1.2
val tuple1 = summonAll[Int *: String *: Double *: EmptyTuple]   // <2>
assert(tuple1 == (10, "foo", 1.2))
