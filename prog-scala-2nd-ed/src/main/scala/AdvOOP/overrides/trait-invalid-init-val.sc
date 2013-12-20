// code-examples/AdvOOP/overrides/trait-invalid-init-val-script.scala
// ERROR: "value" read before initialized.

trait AbstractT2 {
  println("In AbstractT2:")
  val value: Int
  val inverse = 1.0/value      // ???
  println("AbstractT2: value = "+value+", inverse = "+inverse)
}

val c2b = new AbstractT2 {
  println("In c2b:")
  val value = 10
}
println("c2b.value = "+c2b.value+", inverse = "+c2b.inverse)