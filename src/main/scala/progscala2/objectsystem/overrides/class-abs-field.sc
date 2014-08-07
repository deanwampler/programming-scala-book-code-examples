// src/main/scala/progscala2/objectsystem/overrides/class-abs-field.sc

abstract class AbstractC1 {
  val name: String
  var count: Int
}

class ClassWithAbstractC1 extends AbstractC1 {
  val name = "ClassWithAbstractC1"
  var count = 1
}

val c = new ClassWithAbstractC1()
println(c.name)
println(c.count)
