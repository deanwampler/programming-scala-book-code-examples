// src/main/scala/FP/basics/list-map-closure-example.sc

var factor = 3
val multiplier = (i:Int) => i * factor

val l1 = List(1, 2, 3, 4, 5) map multiplier

factor = 5
val l2 = List(1, 2, 3, 4, 5) map multiplier

println(l1)
println(l2)
