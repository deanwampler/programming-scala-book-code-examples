// src/script/scala/progscala3/metaprogramming/ConstValueTuple.scala

import compiletime.constValueTuple

val tuple2 =
	constValueTuple["foo" *: "bar" *: 10 *: 2.5 *: EmptyTuple]    // <1>
println(tuple2 == ("foo", "bar", 10, 2.5))
