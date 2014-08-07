// src/main/scala/progscala2/typesystem/abstracttypes/abstract-types-ex.sc

trait exampleTrait {
  type t1               // t1 is unconstrained
  type t2 >: t3 <: t1   // t2 must be a supertype of t3 and a subtype of t1
  type t3 <: t1         // t3 must be a subtype of t1
  type t4 <: Seq[t1]    // t4 must be a subtype of Seq of t1
  // type t5 = +AnyRef  // ERROR: Can't use variance annotations

  val v1: t1            // Can't initialize with value until t1 defined.
  val v2: t2            // ditto...
  val v3: t3            // ...
  val v4: t4            // ...
}

trait T1 { val name1: String }
trait T2 extends T1 { val name2: String }
case class C(name1: String, name2: String) extends T2

object example extends exampleTrait {
  type t1 = T1
  type t2 = T2
  type t3 = C
  type t4 = Vector[T1]

  val v1 = new T1 { val name1 = "T1"}
  val v2 = new T2 { val name1 = "T1"; val name2 = "T2" }
  val v3 = C("1", "2")
  val v4 = Vector(C("3", "4"))
}
