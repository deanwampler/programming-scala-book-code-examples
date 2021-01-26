// tag::include[]
// src/script/scala/progscala3/objectsystem/init/BadFieldInitOrder.scala

trait T1:
  val denominator: Int
  val inverse = 1.0/denominator                                 // <1>

val obj1 = new T1:                                              // <2>
  val denominator = 10

println(s"obj1: denominator = ${obj1.denominator}, inverse = ${obj1.inverse}")
// end::include[]

assert(obj1.denominator == 10)
assert(obj1.inverse.isPosInfinity)
