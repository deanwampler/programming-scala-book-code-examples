// src/main/scala/progscala2/objectsystem/overrides/trait-val.sc

trait T1 {
  val name = "T1"
}

class Base 

class ClassWithT1 extends Base with T1 {
  override val name = "ClassWithT1"
}

class ClassExtendsT1 extends T1 {
  override val name = "ClassExtendsT1"
}

val c1 = new ClassWithT1()
assert(c1.name == "ClassWithT1")

val c2 = new ClassExtendsT1()
assert(c2.name == "ClassExtendsT1")
