// src/script/scala/progscala3/patternmatching/Assignments.scala

case class Address(street: String, city: String, country: String)
case class Person(name: String, age: Int, address: Address)
val addr = Address("1 Scala Way", "CA", "USA")
val dean = Person("Dean", 29, addr)

val Person(name, age, Address(_, state, _)) = dean

val people = (0 to 4).map {
  i => Person(s"Name$i", 10+i, Address(s"$i Main Street", "CA", "USA"))
}
val nas = for
  Person(name, age, Address(_, state, _)) <- people
yield (name, age, state)
