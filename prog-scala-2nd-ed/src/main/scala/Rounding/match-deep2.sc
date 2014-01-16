// src/main/scala/Rounding/match-deep.sc

// Simplicistic address type. Using all strings is bad, too.
case class Address(street: String, city: String, country: String)

case class Person(name: String, age: Int, address: Address)

val alice   = Person("Alice",   25, Address("1 Scala Lane", "Chicago", "USA"))
val bob     = Person("Bob",     29, Address("2 Java Ave.",  "Miami",   "USA"))
val charlie = Person("Charlie", 32, Address("3 Python Ct.", "Boston",  "USA"))

for (person <- List(alice, bob, charlie)) {
  person match {
    case p @ Person("Alice", 25, _) => println(s"Hi Alice! $p")
    case p @ Person("Bob", 29, Address("2 Java Ave.", "Miami", "USA")) => 
      println(s"Hi Bob! $p")
    case p @ Person(name, age, _) => 
      println(s"Who are you, $age year-old person named $name? $p")
  }
}
