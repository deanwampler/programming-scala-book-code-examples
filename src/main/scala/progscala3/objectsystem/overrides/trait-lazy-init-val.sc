// src/main/scala/progscala3/objectsystem/overrides/trait-lazy-init-val.sc

trait AbstractT2 {
  println("In AbstractT2:")
  val value: Int
  lazy val inverse = 1.0/value      // <1>
}

val obj2 = new AbstractT2 {
  println("In obj2:")
  val value = 10
}
println("obj2.value = "+obj2.value+", inverse = "+obj2.inverse)
assert(obj2.value   == 10)
assert(obj2.inverse == 0.1)
