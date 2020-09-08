// src/main/scala/progscala3/implicits/DomainConcepts.scala

package progscala3.implicits

sealed trait DomainConcept
case class Address(street: String, city: String) extends DomainConcept
case class Person(name: String, address: Address) extends DomainConcept
