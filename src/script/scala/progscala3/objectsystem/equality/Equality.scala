// tag::definitions[]
// src/script/scala/progscala3/objectsystem/equality/Equality.scala

case class Person(firstName: String, lastName: String, age: Int)

val p1a = Person("Dean", "Wampler", 29)
val p1b = Person("Dean", "Wampler", 29)
val p2  = Person("Buck", "Trends",  30)
// end::definitions[]

// tag::equals1[]
assert((p1a.equals(p1a))  == true)
assert((p1a.equals(p1b))  == true)
assert((p1a.equals(p2))   == false)
assert((p1a.equals(null)) == false)
// end::equals1[]

// tag::equals2[]
assert((p1a == p1a)   == true)
assert((p1a == p1b)   == true)
assert((p1a == p2)    == false)
assert((p1a == null)  == false)

assert((p1a != p1a)   == false)
assert((p1a != p1b)   == false)
assert((p1a != p2)    == true)
assert((p1a != null)  == true)
// end::equals2[]

// tag::equals3[]
assert((null == p1a) == false)
assert((p1a == null) == false)
assert((null != p1a) == true)
assert((p1a != null) == true)
// end::equals3[]

// tag::equals4[]
assert((p1a eq p1a)    == true)
assert((p1a eq p1b)    == false) // But p1a == p1b
assert((p1a eq p2)     == false)
assert((p1a eq null)   == false)
assert((null eq p1a)   == false)
assert((null eq null)  == true)
// end::equals4[]

// tag::equals5[]
assert((p1a ne p1a)    == false)
assert((p1a ne p1b)    == true)
assert((p1a ne p2)     == true)
assert((p1a ne null)   == true)
assert((null ne p1a)   == true)
assert((null ne null)  == false) // Compiler warns that it's always false.
// end::equals5[]

// tag::equals6[]
val a1 = Array(1, 2)
val a2 = Array(1, 2)
assert((a1.equals(a1)) == true)
assert((a1.equals(a2)) == false)
// end::equals6[]

// tag::equals7[]
assert((a1.sameElements(a1)) == true)
assert((a1.sameElements(a2)) == true)
// end::equals7[]

// tag::equals8[]
val s1 = Seq(1, 2)
val s2 = Seq(1, 2)
assert((s1 == s1) == true)
assert((s1 == s2) == true)
assert((s1.sameElements(s2)) == true)

val m1 = Map("one" -> 1, "two" -> 2)
val m2 = Map("one" -> 1, "two" -> 2)
assert((m1 == m1) == true)
assert((m1 == m2) == true)
assert((m1.toSeq.sameElements(m2.toSeq)) == true)
// end::equals8[]
