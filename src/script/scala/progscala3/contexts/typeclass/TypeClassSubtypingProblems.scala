// src/script/scala/progscala3/contexts/TypeClassesSubtypingProblems.scala
// This file does not appear in the book.
import progscala3.contexts.json.ToJSON
import progscala3.contexts.{Address, Person}

// NOTE: This file is designed to be copied and pasted into the REPL several
// pieces at a time, not run all at once. All at once causes the behavior to
// change because of the order in which implicits are defined.
// Copy and paste each block between // START N ... // END N, one at a time.
// The DomainConcept, Address, and Person types are defined in
// src/main/scala/progscala3/contexts/

// START 1

given ToJSON[Address]:
  extension (address: Address) def toJSON(level: Int): String =
    val (outdent, indent) = indentation(level)
    s"""{
      |${indent}"street": "${address.street}",
      |${indent}"city":   "${address.city}",
      |$outdent}""".stripMargin


given ToJSON[Person]:
  extension (person: Person) def toJSON(level: Int): String =
    val (outdent, indent) = indentation(level)
    s"""{
      |${indent}"name":    "${person.name}",
      |${indent}"address": ${person.address.toJSON(level+1)}
      |$outdent}""".stripMargin

val address = Address("1 Scala Lane", "Anytown")
val person = Person("Buck Trends", address)

// We want to use this list:
val list1 = List(address, person)
// END 1

// START 2
// NOTE: This is a separate block because it will throw a compiler error, which
// means the everything above here will get toss. So, we do two copy-pastes.

// We want to map over the list and call toJSON(), but what happens?
// An error is thrown that DomainConcept doesn't have a toJSON method. This is
// because list1 is of type
// List[TypeClassesSubtypingProblems.DomainConcept]

list1.map(_.toJSON())
// END 2

// START 3
// Now as a second copy-paste, define another type class for DomainConcept:

// The best we can do is to define a type class for DomainConcept that manually
// switches on the actual type. This is ugly and you'll have to remember to update
// this method if you change the subtypes of DomainConcept. Note that I declared
// it to be a sealed trait above, which lets the compiler catch some problems.
given ToJSON[DomainConcept]:
  extension (dc: DomainConcept) def toJSON(level: Int = 0): String = dc match
    case person: Person   => person.toJSON(level)
    case address: Address => address.toJSON(level)
    // case person: Person   => new PersonToJSON(person).toJSON(level)
    // case address: Address => new AddressToJSON(address).toJSON(level)

list1.map(_.toJSON())
// END 3

// START 4
// You might wonder why we used, for example:
//   new PersonToJSON(person).toJSON(level)
// instead of:
//   person.toJSON(level)
// Should the latter work, when we have a more specific type?
// Well, try it...

given ToJSON[DomainConcept]:
  extension (dc: DomainConcept) def toJSON(level: Int = 0): String = dc match
    case person: Person   => person.toJSON(level)
    case address: Address => address.toJSON(level)

list1.map(_.toJSON())

// END 4
// An infinite recursion occurs!!

