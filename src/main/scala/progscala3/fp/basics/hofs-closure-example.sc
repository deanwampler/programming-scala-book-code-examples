// src/main/scala/progscala3/fp/basics/hofs-closure-example.sc

var factor = 2
val multiplier = (i: Int) => i * factor

val result1 = (1 to 10) filter (_ % 2 == 0) map multiplier reduce (_ * _)
assert(result1 == 122880)

factor = 3
val result2 = (1 to 10) filter (_ % 2 == 0) map multiplier reduce (_ * _)
assert(result2 == 933120)

