// tag::include[]
// src/script/scala/progscala3/objectsystem/init/DefValInit.scala

trait T3:
  val denominator: Int
  def inverse = 1.0/denominator                                 // <1>

val obj3 = new T3:
  val denominator = 10

println(s"obj3: denominator = ${obj3.denominator}, inverse = ${obj3.inverse}")
// end::include[]

assert(obj3.denominator == 10)
assert(obj3.inverse == 0.1)
