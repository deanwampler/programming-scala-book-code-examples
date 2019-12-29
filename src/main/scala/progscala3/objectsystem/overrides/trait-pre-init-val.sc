// src/main/scala/progscala3/objectsystem/overrides/trait-pre-init-val.sc

trait AbstractT {
  println("In AbstractT:")
  val value: Int
  val inverse = 1.0/value
  println("AbstractT: value = "+value+", inverse = "+inverse)
}

// Deprecated in 2.13. No longer allowed in subsequent versions:
val obj = new {
  // println("In obj:")      // <1>
  val value = 10
} with AbstractT

println("obj.value = "+obj.value+", inverse = "+obj.inverse)
