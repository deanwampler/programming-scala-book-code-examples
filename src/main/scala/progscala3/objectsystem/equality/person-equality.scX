// src/main/scala/progscala3/objectsystem/equality/person-equality.sc

case class Person(firstName: String, lastName: String, age: Int)

val p1a = Person("Dean", "Wampler", 29)
val p1b = Person("Dean", "Wampler", 29)
val p2  = Person("Buck", "Trends",  30)

assert((p1a equals p1a)  == true)
assert((p1a equals p1b)  == true)
assert((p1a equals p2)   == false)
assert((p1a equals null) == false)

Seq(null, p1a) foreach { x =>
  try {
    null equals x  // throws java.lang.NullPointerException
    assert(false, s"Shouldn't be here! x = $x")
  } catch {
    case e: java.lang.NullPointerException => // expected
  }
}

assert((p1a == p1a)    == true)
assert((p1a == p1b)    == true)
assert((p1a == p2)     == false)
assert((p1a == null)   == false)
assert((null == p1a)   == false)
assert((null == null)  == true)  // Compiler warns that it's always true.

assert((p1a != p1a)    == false)
assert((p1a != p1b)    == false)
assert((p1a != p2)     == true)
assert((p1a != null)   == true)
assert((null != p1a)   == true)
assert((null != null)  == false) // Compiler warns that it's always false.

assert((p1a eq p1a)    == true)
assert((p1a eq p1b)    == false)
assert((p1a eq p2)     == false)
assert((p1a eq null)   == false)
assert((null eq p1a)   == false)
assert((null eq null)  == true)  // Compiler warns that it's always true.

assert((p1a ne p1a)    == false)
assert((p1a ne p1b)    == true)
assert((p1a ne p2)     == true)
assert((p1a ne null)   == true)
assert((null ne p1a)   == true)
assert((null ne null)  == false) // Compiler warns that it's always false.

Array(1, 2) == Array(1, 2)              // = false
Array(1, 2) sameElements Array(1, 2)    // = true

Seq(1, 2) == Seq(1, 2)                  // = true
Seq(1, 2) sameElements Seq(1, 2)        // = true

