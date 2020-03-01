// src/main/scala/progscala3/patternmatching/match-deep2.sc

case class Address(street: String, city: String, country: String)
case class Person(name: String, age: Int, address: Address)

val alice   = Person("Alice",   25, Address("1 Scala Lane", "Chicago", "USA"))
val bob     = Person("Bob",     29, Address("2 Java Ave.",  "Miami",   "USA"))
val charlie = Person("Charlie", 32, Address("3 Python Ct.", "Boston",  "USA"))

val results = Seq(alice, bob, charlie) map {
  case p @ Person("Alice", 25, Address(_, "Chicago", _)) => s"Hi Alice! $p"
  case p @ Person("Bob", 29, a @ Address(street, city, country)) =>
    s"Hi ${p.name}! age ${p.age}, in ${a}"
  case p @ Person(name, age, Address(street, city, country)) =>
    s"Who are you, $name (age: $age, city = $city)?"
}
assert(results == Seq(
  "Hi Alice! Person(Alice,25,Address(1 Scala Lane,Chicago,USA))",
  "Hi Bob! age 29, in Address(2 Java Ave.,Miami,USA)",
  "Who are you, Charlie (age: 32, city = Boston)?"))
