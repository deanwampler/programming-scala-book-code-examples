// src/main/scala/progscala2/typesystem/bounds/lower-bounds2.sc

// Won't compile, because if +A is used, then "default" in +getOrElse+
// must be a of type B, where B >: A.
// case class Opt[+A](value: A = null) {
//   def getOrElse(default: A): A = if (value != null) value else default 
// }

case class Opt[A](value: A = null) {
  def getOrElse(default: A): A = if (value != null) value else default 
}

class Parent(val value: Int) {                             // <1>
  override def toString = s"${this.getClass.getName}($value)" 
}
class Child(value: Int) extends Parent(value)

val p4: Parent = Opt(new Child(1)).getOrElse(new Parent(10))
val p5: Parent = Opt[Parent](null).getOrElse(new Parent(10))
val p6: Parent = Opt[Child](null).getOrElse(new Parent(10))
