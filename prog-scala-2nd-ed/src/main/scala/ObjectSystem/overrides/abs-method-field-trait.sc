// src/main/scala/ObjectSystem/overrides/abs-method-field-trait.sc

trait AbstractNameTrait {
  def name: String
}

class ConcreteNameClass extends AbstractNameTrait {
  val name = "ConcreteNameClass"
}

println(new ConcreteNameClass().name)   // => "ConcreteNameClass"
