// src/main/scala/progscala3/objectsystem/overrides/class-abs-field.sc

abstract class AbstractC1 {
  val name: String
  var count: Int
}

class ClassWithAbstractC1 extends AbstractC1 {
  val name = "ClassWithAbstractC1"
  var count = 1
}

val c = new ClassWithAbstractC1()
assert(c.name == "ClassWithAbstractC1")
assert(c.count == 1)
