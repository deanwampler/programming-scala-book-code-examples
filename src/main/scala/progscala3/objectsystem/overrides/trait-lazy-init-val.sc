// src/main/scala/progscala3/objectsystem/overrides/trait-lazy-init-val.sc

trait AbstractT {
  println("In AbstractT:")
  val value: Int
  lazy val inverse = 1.0/value      // <1>
  // println("AbstractT: value = "+value+", inverse = "+inverse)
}

val obj = new AbstractT {
  println("In obj:")
  val value = 10
}
println("obj.value = "+obj.value+", inverse = "+obj.inverse)
