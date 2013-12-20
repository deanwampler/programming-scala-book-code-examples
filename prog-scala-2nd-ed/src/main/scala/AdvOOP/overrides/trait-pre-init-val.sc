// code-examples/AdvOOP/overrides/trait-pre-init-val-script.scala

trait AbstractT2 {
  println("In AbstractT2:")
  val value: Int
  val inverse = 1.0/value
  println("AbstractT2: value = "+value+", inverse = "+inverse)
}

val c2c = new {
  // Only initializations are allowed in pre-init. blocks.
  // println("In c2c:")  
  val value = 10
} with AbstractT2

println("c2c.value = "+c2c.value+", inverse = "+c2c.inverse)