// src/main/scala/progscala3/contexts/DomainConcepts.scala

package progscala3.contexts

sealed trait DomainConcept
case class Address(street: String, city: String) extends DomainConcept
case class Person(name: String, address: Address) extends DomainConcept
