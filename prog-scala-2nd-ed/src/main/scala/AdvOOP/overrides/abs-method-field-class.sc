// code-examples/AdvOOP/overrides/abs-method-field-class-script.scala

abstract class AbstractParent {
  def name: String
}

class ConcreteChild extends AbstractParent {
  val name = "Child"
}

println(new ConcreteChild().name)   // => "Child"
