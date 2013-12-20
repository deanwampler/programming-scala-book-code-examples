// code-examples/AdvOOP/overrides/method-field-trait-script.scala

trait NameTrait {
  def name = "NameTrait"
}

class ConcreteNameClass extends NameTrait {
  override val name = "ConcreteNameClass"
}

println(new ConcreteNameClass().name)   // => "ConcreteNameClass"
