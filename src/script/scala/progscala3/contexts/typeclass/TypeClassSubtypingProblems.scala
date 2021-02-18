// src/script/scala/progscala3/contexts/typeclass/TypeClassSubtypingProblems.scala
// This file does not appear in the book.

// NOTE: This file is designed to be copied and pasted into the REPL several
// pieces at a time, not run all at once. All at once causes the behavior to
// change because of the order in which implicits are defined.
// Copy and paste each block between // START N ... // END N, one at a time.
// The DomainConcept, Address, and Person types are defined in
// src/main/scala/progscala3/contexts/

// START 1
import progscala3.contexts.json.ToJSON
import progscala3.contexts.{DomainConcept, Address, Person}

// Helper methods are used, like in
// src/main/scala/progscala3/contexts/typeclass/new3/ToJSONTypeClasses.scala
// for reasons explained below.
given ToJSON[Address] with
  def toJSON2(address: Address, name: String, level: Int): String =
    val (outdent, indent) = indentation(level)
    s""""$name": {
      |${indent}"street": "${address.street}",
      |${indent}"city":   "${address.city}",
      |$outdent}""".stripMargin
  extension (address: Address)
    def toJSON(name: String = "", level: Int = 0): String =
      toJSON2(address, name, level)

given ToJSON[Person] with
  def toJSON2(person: Person, name: String, level: Int): String =
    val (outdent, indent) = indentation(level)
    s""""$name": {
      |${indent}"name":    "${person.name}",
      |${indent}${person.address.toJSON("address", level+1)}
      |$outdent}""".stripMargin
  extension (person: Person)
    def toJSON(name: String = "", level: Int = 0): String =
      toJSON2(person, name, level)

val address = Address("1 Scala Lane", "Anytown")
val person = Person("Buck Trends", address)

// We want to use this list:
val list1 = List(address, person)
// END 1

// START 2
// NOTE: This is a separate block because it will throw a compiler error, which
// means the everything above here would get tossed, unless pasted separately.
// So, we use two copy-paste steps.

// We want to map over the list and call toJSON(), but what happens?
// An error is thrown that DomainConcept doesn't have a toJSON method. This is
// because list1 is of type
// List[TypeClassesSubtypingProblems.DomainConcept]

list1.map(_.toJSON("object"))
// END 2

// START 3
// Now as a second copy-paste, define another type class for DomainConcept:

// The best we can do is to define a type class for DomainConcept that manually
// switches on the actual type. This is ugly and you'll have to remember to update
// this method if you change the subtypes of DomainConcept. Note that I declared
// it to be a sealed trait above, which lets the compiler catch some problems.
given ToJSON[DomainConcept] with
  extension (dc: DomainConcept)
    def toJSON(name: String = "", level: Int = 0): String = dc match
      case person: Person   => summon[ToJSON[Person]].toJSON2(person, name, level)
      case address: Address => summon[ToJSON[Address]].toJSON2(address, name, level)

list1.map(_.toJSON("object"))
// END 3

// START 4
// You might wonder why we used the givens' helper methods toJSON2, for example:
//   summon[ToJSON[Person]].toJSON(person, name, level)
// instead of:
//   person.toJSON(name, level)
// Shouldn't the latter work, when we have a more specific type?
// Well, an infinite recursion occurs!! This is because, for example,
// person.toJSON() will now recursively call the DomainConcept.toJSON
// extension method, instead of the original Person.toJSON.

given ToJSON[DomainConcept] with
  extension (dc: DomainConcept)
    def toJSON(name: String = "", level: Int = 0): String = dc match
      case person: Person   => person.toJSON(name, level)
      case address: Address => address.toJSON(name, level)

list1.map(_.toJSON())   // BOOM!!
// END 4
