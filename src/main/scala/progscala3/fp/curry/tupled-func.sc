// src/main/scala/progscala3/fp/curry/tupled-func.sc

def mult(d1: Double, d2: Double, d3: Double) = d1 * d2 * d3

val d3 = (2.2, 3.3, 4.4)

mult(d3._1, d3._2, d3._3)

val multTupled = Function.tupled(mult _)
// multTupled: ((Double, Double, Double)) => Double = <function1>

val result1 = multTupled(d3)
assert(result1 > 31.94 && result1 < 31.95)

val multUntupled = Function.untupled(multTupled)

val result2 = multUntupled(d3._1, d3._2, d3._3)
assert(result2 > 31.94 && result2 < 31.95)
