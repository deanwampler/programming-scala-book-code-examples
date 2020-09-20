// src/main/scala/progscala3/basicoop/people/Person.scala
package progscala3.basicoop.people

case class Person(
  name: String,
  age: Option[Int] = None,
  address: Option[Address] = None)

object Person:
  def apply(name: String): Person = new Person(name)
  def apply(name: String, age: Int): Person = new Person(name, Some(age))
  def apply(name: String, age: Int, address: Address): Person =
    new Person(name, Some(age), Some(address))
  def apply(name: String, address: Address): Person =
    new Person(name, address = Some(address))
