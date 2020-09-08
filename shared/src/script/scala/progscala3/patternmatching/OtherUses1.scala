// src/script/scala/progscala3/patternmatching/OtherUses1.scala

case class Address(street: String, city: String, country: String)
case class Person(name: String, age: Int, address: Address)
val addr = Address("1 Scala Way", "CA", "USA")
val pers = Person("Dean", 29, addr)

val Person(name, age, Address(_, state, _)) = p

val seq = 0 to 4
val head1 +: head2 +: tail = seq

val people = seq.map {
  i => Person(s"Name$i", 10+i, Address(s"$i Main Street", "CA", "USA"))
}
val na = for
  Person(name, age, address) <- people
yield (name, age)
