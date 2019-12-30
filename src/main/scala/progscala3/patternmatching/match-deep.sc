// src/main/scala/progscala3/patternmatching/match-deep.sc

// Simplistic address type. Using all strings is questionable, too.
case class Address(street: String, city: String, country: String)
case class Person(name: String, age: Int, address: Address)

val alice   = Person("Alice",   25, Address("1 Scala Lane", "Chicago", "USA"))
val bob     = Person("Bob",     29, Address("2 Java Ave.",  "Miami",   "USA"))
val charlie = Person("Charlie", 32, Address("3 Python Ct.", "Boston",  "USA"))

val results = Seq(alice, bob, charlie) map {
  case Person("Alice", 25, Address(_, "Chicago", _)) => "Hi Alice!"
  case Person("Bob", 29, Address("2 Java Ave.", "Miami", "USA")) => "Hi Bob!"
  case Person(name, age, _) => s"Who are you, $name (age: $age)?"
}
assert(results == Seq(
  "Hi Alice!",
  "Hi Bob!",
  "Who are you, Charlie (age: 32)?"))
