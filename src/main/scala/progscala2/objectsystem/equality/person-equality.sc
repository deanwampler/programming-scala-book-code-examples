// src/main/scala/progscala2/objectsystem/equality/person-equality.sc

case class Person(firstName: String, lastName: String, age: Int)

val p1a = Person("Dean", "Wampler", 29)
val p1b = Person("Dean", "Wampler", 29)
val p2  = Person("Buck", "Trends",  30)

p1a equals p1a     // = true
p1a equals p1b     // = true
p1a equals p2      // = false
p1a equals null    // = false
try {
  null equals p1a  // throws java.lang.NullPointerException
} catch {
  case e: java.lang.NullPointerException => e
}
try {
  null equals null // throws java.lang.NullPointerException
} catch {
  case e: java.lang.NullPointerException => e
}

p1a == p1a         // = true
p1a == p1b         // = true
p1a == p2          // = false
p1a == null        // = false
null == p1a        // = false
null == null       // = true  (compiler warns that it's always true.)

p1a != p1a         // = false
p1a != p1b         // = false
p1a != p2          // = true
p1a != null        // = true
null != p1a        // = true
null != null       // = false (compiler warns that it's always false.)

p1a eq p1a         // = true
p1a eq p1b         // = false
p1a eq p2          // = false
p1a eq null        // = false
null eq p1a        // = false
null eq null       // = true  (compiler warns that it's always true.)

p1a ne p1a         // = false
p1a ne p1b         // = true
p1a ne p2          // = true
p1a ne null        // = true
null ne p1a        // = true
null ne null       // = false (compiler warns that it's always false.)

Array(1, 2) == Array(1, 2)              // = false
Array(1, 2) sameElements Array(1, 2)    // = true

List(1, 2) == List(1, 2)                // = true
List(1, 2) sameElements List(1, 2)      // = true

