// src/script/scala/progscala3/implicits/DomainConcepts.scala

sealed trait DomainConcept
case class Address(street: String, city: String)  extends DomainConcept
case class Person(name: String, address: Address) extends DomainConcept

val address = Address("1 Scala Lane", "Anytown")
val person = Person("Buck Trends", address)
