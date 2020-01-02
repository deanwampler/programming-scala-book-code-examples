// src/main/scala/progscala3/fp/basics/hofs-example.sc

val result = (1 to 10) filter (_ % 2 == 0) map (_ * 2) reduce (_ * _)
assert(result == 122880)
