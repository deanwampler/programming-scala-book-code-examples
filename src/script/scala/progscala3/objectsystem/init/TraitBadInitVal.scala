// src/script/scala/progscala3/objectsystem/overrides/TraitBadInitVal.scala

trait AbstractT1:
  println("In AbstractT1:")
  val value: Int
  val inverse = 1.0/value      // <1>
  println(s"end of AbstractT1: value = $value, inverse = $inverse")

val obj1 = new AbstractT1:
  val value = 10
  println(s"End of obj1: value = $value, inverse = $inverse")

assert(obj1.value   == 10)
assert(obj1.inverse.isPosInfinity)
