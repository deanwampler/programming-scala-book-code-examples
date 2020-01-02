// src/main/scala/progscala3/objectsystem/overrides/trait-bad-init-val.scX
// ERROR: "value" read before initialized.

trait AbstractT1 {
  println("In AbstractT1:")
  val value: Int
  val inverse = 1.0/value      // <1>
}

val obj1 = new AbstractT1 {
  println("In obj1:")
  val value = 10
}
println("obj1.value = "+obj1.value+", inverse = "+obj1.inverse)
assert(obj1.value   == 10)
assert(obj1.inverse.isPosInfinity)
