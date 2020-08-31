// src/main/scala/progscala3/basicoop/people/PersonAuxConstructors.scala
package progscala3.basicoop.people

case class PersonAuxConstructors(
  	name: String,
  	age: Option[Int] = None,
  	address: Option[Address] = None) derives Eql:

  def this(name: String) = this(name, None, None)

  def this(name: String, age: Int) = this(name, Some(age), None)

  def this(name: String, age: Int, address: Address) =
    this(name, Some(age), Some(address))

  def this(name: String, address: Address) =
  	this(name, None, Some(address))
