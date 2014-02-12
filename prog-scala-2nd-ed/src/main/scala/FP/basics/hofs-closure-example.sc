// src/main/scala/FP/basics/hofs-closure-example.sc

var factor = 2
val multipler = (i: Int) => i * factor

(1 to 10) filter (_ % 2 == 0) map multipler reduce (_ * _)

factor = 3
(1 to 10) filter (_ % 2 == 0) map multipler reduce (_ * _)

