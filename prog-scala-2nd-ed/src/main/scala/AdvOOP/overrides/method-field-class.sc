// code-examples/AdvOOP/overrides/method-field-class-script.scala

class Parent {
  def name = "Parent"
}

class Child extends Parent {
  override val name = "Child"
}

println(new Child().name)   // => "Child"
