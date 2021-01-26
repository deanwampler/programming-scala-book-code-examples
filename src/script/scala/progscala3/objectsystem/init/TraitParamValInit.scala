// tag::include[]
// src/script/scala/progscala3/objectsystem/init/TraitParamValInit.scala

trait T4(val denominator: Int):                                 // <1>
  val inverse = 1.0/denominator                                 // <2>

val obj4 = new T4(10) {}                                        // <3>

println(s"obj4: denominator = ${obj4.denominator}, inverse = ${obj4.inverse}")
// end::include[]

assert(obj4.denominator == 10)
assert(obj4.inverse == 0.1)
