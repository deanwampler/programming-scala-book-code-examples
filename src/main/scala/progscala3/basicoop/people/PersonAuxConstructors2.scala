// src/main/scala/progscala3/basicoop/people/PersonAuxConstructors2.scala
package progscala3.basicoop.people

case class PersonWithApply(
  	name: String,
  	age: Option[Int] = None,
  	address: Option[Address] = None)

object PersonWithApply:
  def apply(name: String, age: Int): PersonWithApply =
  	apply(name, Some(age))

  def apply(name: String, age: Int, address: Address): PersonWithApply =
    apply(name, Some(age), Some(address))

  def apply(name: String, address: Address): PersonWithApply =
  	apply(name, None, Some(address))
