// src/script/scala/progscala3/typesystem/bounds/LowerBounds.scala

class Parent(val value: Int):                              // <1>
  override def toString = s"${this.getClass.getName}($value)"
class Child(value: Int) extends Parent(value)

var optParent: Option[Parent] = Option(new Child(1))       // <2>
val parent1: Parent = optParent.getOrElse(new Child(0))    // <3>
optParent = None
val parent2: Parent = optParent.getOrElse(new Child(0))    // <4>
val parent3: Parent = optParent.getOrElse(new Parent(0))   // <4>
