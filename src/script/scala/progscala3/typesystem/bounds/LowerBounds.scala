// src/script/scala/progscala3/typesystem/bounds/LowerBounds.scala

class Super(val value: Int):                               // <1>
  override def toString = s"${this.getClass.getSimpleName}($value)"
class Sub(value: Int) extends Super(value)

val optSub: Option[Sub] = Some(new Sub(1))
var optSuper: Option[Super] = optSub                       // <2>
val super1: Super = optSuper.getOrElse(new Sub(0))         // <3>
optSuper = None                                            // <4>
val super2: Super = optSuper.getOrElse(new Super(0))       // <5>
