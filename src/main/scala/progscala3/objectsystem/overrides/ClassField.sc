// src/main/scala/progscala3/objectsystem/overrides/class-field.sc

class C1 {
  val name = "C1"
  var count = 0
}

class ClassWithC1 extends C1 {
  override val name = "ClassWithC1"
  count = 1
}

val c = new ClassWithC1()
assert(c.name == "ClassWithC1")
assert(c.count == 1)
