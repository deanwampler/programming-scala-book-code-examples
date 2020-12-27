// tag::declarations[]
// src/script/scala/progscala3/typesystem/intersectionunion/Intersection.scala
trait M:
  def m(s: String): String = s
trait T1 extends M:
  override def m(s: String): String = s"[ ${super.m(s)} ]"
trait T2 extends M:
  override def m(s: String): String = s"( ${super.m(s)} )"
open class C extends M:
  override def m(s: String): String = s"{ ${super.m(s)} }"
// end::declarations[]

// tag::example[]
val c12 = new C with T1 with T2
val c21 = new C with T2 with T1

assert(c12.m("hello") == "( [ { hello } ] )")
assert(c21.m("hello") == "[ ( { hello } ) ]")
// end::example[]

// tag::commutativity[]
val c12a: C & T1 & T2 = c12
val c12b: C & T2 & T1 = c12
val c12c: T1 & C & T2 = c12
val c12d: T2 & C & T1 = c12
val c12e: T1 & T2 & C = c12
val c12f: T2 & T1 & C = c12
// end::commutativity[]

// tag::membership[]
val t1a: T1 = c12
val t2a: T2 = c12
val c2a: C  = c12

val t12: T1 & T2 = c12
val ct1: C  & T1 = c12
val ct2: C  & T2 = c12
// end::membership[]

// tag::functions[]
def f(t12: T1 & T2): String = t12.m("hello!")
val list12: Seq[T1 & T2] = Seq(c12, c21)
assert(list12.map(f) == List("( [ { hello! } ] )", "[ ( { hello! } ) ]"))
// end::functions[]

// tag::covariance[]
val listt1t2: Seq[T1 & T2] = Seq(c12, c21)
val list1: Seq[T1] = listt1t2
val list2: Seq[T2] = listt1t2
val list3: Seq[T1] & Seq[T2] = listt1t2
// end::covariance[]

f(list1.head)   // ERROR: "Found T1, Required T1 & T2"
f(list2.head)   // ERROR: "Found T2, Required T1 & T2"
