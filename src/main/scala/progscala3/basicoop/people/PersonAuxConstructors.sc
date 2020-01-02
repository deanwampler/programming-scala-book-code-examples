// src/main/scala/progscala3/basicoop/people/PersonAuxConstructors.sc
import progscala3.basicoop.people.{Address, PersonAuxConstructors, ZipCode}

val addr = Address("1 Scala Lane", "Bay Area", "CA", ZipCode(98765))
println(addr)  // Result: Address(1 Scala Lane,Bay Area,CA,98765)

// primary constructor
val p1 = new PersonAuxConstructors("Buck Trends2", Some(20), Some(addr))
assert(p1.name    == "Buck Trends2")
assert(p1.age     == Some(20))
assert(p1.address == Some(addr))

val p2 = new PersonAuxConstructors("Buck Trends3", 20, addr)
assert(p2.name    == "Buck Trends3")
assert(p2.age     == Some(20))
assert(p2.address == Some(addr))

val p3 = new PersonAuxConstructors("Buck Trends4", 20)
assert(p3.name    == "Buck Trends4")
assert(p3.age     == Some(20))
assert(p3.address == None)

val p4 = new PersonAuxConstructors("Buck Trends1")
assert(p4.name    == "Buck Trends1")
assert(p4.age     == None)
assert(p4.address == None)
