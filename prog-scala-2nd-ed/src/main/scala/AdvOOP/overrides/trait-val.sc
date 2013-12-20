// code-examples/AdvOOP/overrides/trait-val-script.scala
// DANGER! Silent failure to override a trait's "name" (V2.7.5 only).
// Works as expected in V2.8.0.

trait T1 {
  val name = "T1"
}

class Base 

class ClassWithT1 extends Base with T1 {
  override val name = "ClassWithT1"
}

val c = new ClassWithT1()
println(c.name)

class ClassExtendsT1 extends T1 {
  override val name = "ClassExtendsT1"
}

val c2 = new ClassExtendsT1()
println(c2.name)
