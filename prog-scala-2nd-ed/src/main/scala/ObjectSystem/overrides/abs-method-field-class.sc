// src/main/scala/AdvOOP/overrides/abs-method-field-class.sc

abstract class AbstractParent {
  def name: String
}

class ConcreteChild extends AbstractParent {
  val name = "Child"
}

println(new ConcreteChild().name)   // => "Child"
