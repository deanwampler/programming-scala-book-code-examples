// src/main/scala/progscala3/typesystem/bounds/lower-bounds.sc

class Parent(val value: Int) {                   // <1>
  override def toString = s"${this.getClass.getName}($value)" 
}
class Child(value: Int) extends Parent(value)

val op1: Option[Parent] = Option(new Child(1))   // <2>
val p1: Parent = op1.getOrElse(new Parent(10))
assert(p1.value == new Child(1).value)           // <3>

val op2: Option[Parent] = Option[Parent](null)   // <4>
val p2a: Parent = op2.getOrElse(new Parent(10))
val p2b: Parent = op2.getOrElse(new Child(100))
assert(op2 == None)
assert(p2a.value == new Parent(10).value)
assert(p2b.value == new Child(100).value)

val op3: Option[Parent] = Option[Child](null)    // <5>
val p3a: Parent = op3.getOrElse(new Parent(20))
val p3b: Parent = op3.getOrElse(new Child(200))
assert(op3 == None)
assert(p3a.value == new Parent(20).value)
assert(p3b.value == new Child(200).value)
