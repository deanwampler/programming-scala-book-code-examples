// src/script/scala/progscala3/typesystem/bounds/LowerBounds.scala

class Parent(val value: Int):                              // <1>
  override def toString = s"${this.getClass.getSimpleName}($value)"
class Child(value: Int) extends Parent(value)

val optChild: Option[Child] = Some(new Child(1))
var optParent: Option[Parent] = optChild                   // <2>
val parent1: Parent = optParent.getOrElse(new Child(0))    // <3>
optParent = None                                           // <4>
val parent2: Parent = optParent.getOrElse(new Parent(0))   // <5>
