// tag::ExampleTrait[]
// src/script/scala/progscala3/typesystem/abstracttypes/AbstractTypesEx.scala
// NOTE: This example doesn't appear in the book.
trait ExampleTrait:
  type t1               // t1 is unconstrained
  type t2 >: t3 <: t1   // t2 must be a supertype of t3 and a subtype of t1
  type t3 <: t1         // t3 must be a subtype of t1
  type t4 <: Seq[t1]    // t4 must be a subtype of Seq of t1
  // type t5 = +AnyRef  // ERROR: Can't use variance annotations

  val v1: t1            // Can't initialize with value until t1 defined.
  val v2: t2            // ditto...
  val v3: t3            // ...
  val v4: t4            // ...
// end::ExampleTrait[]

// tag::ExampleTraitUsage[]
trait T1 { val name1: String }
trait T2 extends T1 { val name2: String }
case class C(name1: String, name2: String) extends T2
// end::ExampleTraitUsage[]

// tag::Example[]
object Example extends ExampleTrait:
  type t1 = T1
  type t2 = T2
  type t3 = C
  type t4 = Vector[T1]

  val v1 = new T1 { val name1 = "T1" }
  val v2 = new T2 { val name1 = "T1"; val name2 = "T2" }
  val v3 = C("1", "2")
  val v4 = Vector(C("3", "4"))

  override def toString(): String =
    val v1str = s"v1: name1 = ${v1.name1}"
    val v2str = s"v2: name1 = ${v2.name1}, name2 = ${v2.name2}"
    s"Example: v1 = $v1str, v2 = $v2str, v3 = $v3, v4 = $v4"

assert(Example.toString ==
  "Example: v1 = v1: name1 = T1, v2 = v2: name1 = T1, " +
  "name2 = T2, v3 = C(1,2), v4 = Vector(C(3,4))")
// end::Example[]
