// src/main/scala/progscala2/typesystem/recursivetypes/f-bound.sc

trait Parent[T <: Parent[T]] {                                       // <1>
  def make: T 
}

case class Child1(s: String) extends Parent[Child1] {                // <2>
  def make: Child1 = Child1(s"Child1: make: $s")
}

case class Child2(s: String) extends Parent[Child2] {
  def make: Child2 = Child2(s"Child2: make: $s")
}

val c1  = Child1("c1")            // c1: Child1 = Child1(c1)
val c2  = Child2("c2")            // c2: Child2 = Child2(c2)
val c11 = c1.make                 // c11: Child1 = Child1(Child1: make: c1)
val c22 = c2.make                 // c22: Child2 = Child2(Child2: make: c2)

val p1: Parent[Child1] = c1       // p1: Parent[Child1] = Child1(c1)
val p2: Parent[Child2] = c2       // p2: Parent[Child2] = Child2(c2)
val p11 = p1.make                 // p11: Child1 = Child1(Child1: make: c1)
val p22 = p2.make                 // p22: Child2 = Child2(Child2: make: c2)
