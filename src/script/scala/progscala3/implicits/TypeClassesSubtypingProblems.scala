// src/script/scala/progscala3/implicits/TypeClassesSubtypingProblems.scala
// This program does not appear in the book.

// NOTE: This file is designed to be copy and pasted into the REPL pieces
// at a time, not run all at once.

// Copy and paste each block between // START N ... // END N, one at a time.

// START 1
sealed trait DomainConcept
case class Address(street: String, city: String, state: String, zip: String)
  extends DomainConcept
case class Person(name: String, age: Int, address: Address)
  extends DomainConcept

trait ToJSON[+T] {
  def toJSON(level: Int = 0): String

  val INDENTATION = "  "
  def indentation(level: Int = 0): (String,String) =
    (INDENTATION * level, INDENTATION * (level+1))
}

implicit class AddressToJSON(address: Address) extends ToJSON[Address] {
  def toJSON(level: Int = 0): String = {
    val (outdent, indent) = indentation(level)
    s"""{
      |${indent}"street": "${address.street}",
      |${indent}"city":   "${address.city}",
      |${indent}"state":  "${address.state}",
      |${indent}"zip":    "${address.zip}"
      |$outdent}""".stripMargin
  }
}

implicit class PersonToJSON(person: Person) extends ToJSON[Person] {
  def toJSON(level: Int = 0): String = {
    val (outdent, indent) = indentation(level)
    s"""{
      |${indent}"name":    "${person.name}",
      |${indent}"age":     "${person.age}",
      |${indent}"address": ${new AddressToJSON(person.address).toJSON(level+1)}
      |$outdent}""".stripMargin
  }
}

val a = Address("1 Scala Lane", "Anytown", "CA", "98765")

// We want to use this list:
val list1 = List(a, Person("Buck Trends", 29, a))
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
implicit class DomainConceptToJSON(dc: DomainConcept) extends ToJSON[DomainConcept] {
  def toJSON(level: Int = 0): String = dc match {
    case person: Person   => new PersonToJSON(person).toJSON(level)
    case address: Address => new AddressToJSON(address).toJSON(level)
  }
}

list1.map(_.toJSON())
// END 3

// START 4
// You might wonder why we used, for example:
//   new PersonToJSON(person).toJSON(level)
// instead of:
//   person.toJSON(level)
// Should the latter work, when we have a more specific type?
// Well, try it...

implicit class DomainConceptToJSON(dc: DomainConcept) extends ToJSON[DomainConcept] {
  def toJSON(level: Int = 0): String = dc match {
    case person: Person   => person.toJSON(level)
    case address: Address => address.toJSON(level)
  }
}
list1.map(_.toJSON())

// END 4
// An infinite recursion occurs.

