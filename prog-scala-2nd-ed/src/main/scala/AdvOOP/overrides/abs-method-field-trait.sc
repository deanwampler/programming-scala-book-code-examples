// code-examples/AdvOOP/overrides/abs-method-field-trait-script.scala

trait AbstractNameTrait {
  def name: String
}

class ConcreteNameClass extends AbstractNameTrait {
  val name = "ConcreteNameClass"
}

println(new ConcreteNameClass().name)   // => "ConcreteNameClass"
