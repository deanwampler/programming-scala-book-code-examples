// src/script/scala/progscala3/fp/basics/Tupling.scala
def mult(d1: Double, d2: Double) = d1 * d2
val d23 = (2.2, 3.3)
val d = mult(d23._1, d23._2)

val multTupled1 = Function.tupled(mult)
val multTupled2 = mult.tupled
val d2 = multTupled1(d23)
val d3 = multTupled2(d23)

val mult2 = Function.untupled(multTupled2)  // Go back...
val d4 = mult2(d23._1, d23._2)

// val mult2b = multTupled2.untupled  // Not a defined method
