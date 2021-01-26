// tag::include[]
// src/script/scala/progscala3/objectsystem/init/LazyValInit.scala

trait T2:
  val denominator: Int
  lazy val inverse = 1.0/denominator                            // <1>

val obj2 = new T2:
  val denominator = 10

println(s"obj2: denominator = ${obj2.denominator}, inverse = ${obj2.inverse}")
// end::include[]

assert(obj2.denominator == 10)
assert(obj2.inverse == 0.1)
