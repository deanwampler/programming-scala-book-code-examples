// src/main/scala/progscala2/objectsystem/overrides/trait-lazy-init-val.sc

trait AbstractT2 {
  println("In AbstractT2:")
  val value: Int
  lazy val inverse = 1.0/value      // <1>
  // println("AbstractT2: value = "+value+", inverse = "+inverse)
}

val obj = new AbstractT2 {
  println("In obj:")
  val value = 10
}
println("obj.value = "+obj.value+", inverse = "+obj.inverse)
