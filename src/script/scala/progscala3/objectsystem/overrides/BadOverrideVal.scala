// tag::include[]
// src/script/scala/progscala3/objectsystem/overrides/BadOverrideVal.scala

trait T1:
  println("In T1:")
  val value: Int = 100
  val inverse = 1.0/value                                       // <1>
  println(s"end of T1: value = $value, inverse = $inverse")

val obj1 = new T1:
  override val value = 10                                       // <2>
  println(s"End of obj1: value = $value, inverse = $inverse")
// end::include[]

assert(obj1.value == 10)
assert(obj1.inverse > 0.009 && obj1.inverse < 0.011)
