// src/main/scala/AdvOOP/overrides/method-field-trait.sc

trait NameTrait {
  def name = "NameTrait"
}

class ConcreteNameClass extends NameTrait {
  override val name = "ConcreteNameClass"
}

println(new ConcreteNameClass().name)   // => "ConcreteNameClass"
