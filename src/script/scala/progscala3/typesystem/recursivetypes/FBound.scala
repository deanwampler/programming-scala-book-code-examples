// tag::definitions[]
// src/script/scala/progscala3/typesystem/recursivetypes/FBound.scala

trait Super[T <: Super[T]]:                                     // <1>
  def make: T                                                   // <2>

case class Sub1(s: String) extends Super[Sub1]:                 // <3>
  def make: Sub1 = Sub1(s"Sub1: make: $s")

case class Sub2(s: String) extends Super[Sub2]:
  def make: Sub2 = Sub2(s"Sub2: make: $s")

// case class Foo(str:String)                                   // <4>
// case class Odd(s: String) extends Super[Foo]:
//   def make: Foo = Foo(s"Foo: make: $s")
// end::definitions[]

val s1  = Sub1("s1")              // s1: Sub1 = Sub1(s1)
val s2  = Sub2("s2")              // s2: Sub2 = Sub2(s2)
val s11 = s1.make                 // s11: Sub1 = Sub1(Sub1: make: s1)
val s22 = s2.make                 // s22: Sub2 = Sub2(Sub2: make: s2)
assert(s11.s == "Sub1: make: s1")
assert(s22.s == "Sub2: make: s2")

val sup1: Super[Sub1] = s1        // sup1: Super[Sub1] = Sub1(s1)
val sup2: Super[Sub2] = s2        // sup2: Super[Sub2] = Sub2(s2)
val sup11 = sup1.make             // sup11: Sub1 = Sub1(Sub1: make: s1)
val sup22 = sup2.make             // sup22: Sub2 = Sub2(Sub2: make: s2)
assert(sup11.s == "Sub1: make: s1")
assert(sup22.s == "Sub2: make: s2")
