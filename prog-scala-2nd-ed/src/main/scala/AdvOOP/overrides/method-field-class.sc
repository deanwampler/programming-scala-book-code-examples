// src/main/scala/AdvOOP/overrides/method-field-class.sc

class Parent {
  def name = "Parent"
}

class Child extends Parent {
  override val name = "Child"
}

println(new Child().name)   // => "Child"
