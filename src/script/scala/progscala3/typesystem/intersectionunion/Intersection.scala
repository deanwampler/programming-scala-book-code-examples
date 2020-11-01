// tag::declarations[]
// src/script/scala/progscala3/typesystem/intersectionunion/Intersection.scala
trait M:
  def m(s: String): String = s
trait T1 extends M:
  override def m(s: String): String = s"[ ${super.m(s)} ]"
  val name1 = "T1"
trait T2 extends M:
  override def m(s: String): String = s"( ${super.m(s)} )"
  val name2 = "T2"
class C extends M:
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
val c12b: C & T1 & T2 = c21

val c21a: C & T2 & T1 = c12
val c21b: C & T2 & T1 = c21

val _21ca: T2 & T1 & C = c12
val _21cb: T2 & T1 & C = c21
// end::commutativity[]

// tag::membership[]
val t1a: T1 = c12
val t2a: T2 = c12
val c2a: C  = c12

val t1b: T1 = c21
val t2b: T2 = c21
val c2b: C  = c21

val t12: T1 & T2 = c21
val t21: T2 & T1 = c21
val c1:  C  & T1 = c21
val c2:  C  & T2 = c21
// end::membership[]

// tag::functions[]
def f(t12: T1 & T2): String = t12.m("hello!")
val list12: Seq[T1 & T2] = Seq(c12, c21)
assert(list12.map(f) == List("( [ { hello! } ] )", "[ ( { hello! } ) ]"))
// end::functions[]

// tag::covariance[]
val list1: Seq[T1] = list12
val list2: Seq[T2] = list12
val list12b: Seq[T1] & Seq[T2] = list12
f(list1.head)   // Error: "Found T1, Required T1 & T2"
f(list2.head)   // Error: "Found T2, Required T1 & T2"
// tag::covariance[]
