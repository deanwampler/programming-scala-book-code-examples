// src/main/scala/progscala2/objectsystem/overrides/abs-method-field-trait.sc

trait AbstractNameTrait {
  def name: String
}

class ConcreteNameClass extends AbstractNameTrait {
  val name = "ConcreteNameClass"
}

println(new ConcreteNameClass().name)   // => "ConcreteNameClass"
