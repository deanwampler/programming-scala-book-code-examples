// src/script/scala/progscala3/fp/curry/Tupleing.scala

def mult(d1: Double, d2: Double, d3: Double) = d1 * d2 * d3
val multTupled = Function.tupled(mult)

val d3 = (2.2, 3.3, 4.4)

mult(d3._1, d3._2, d3._3)
multTupled(d3)
