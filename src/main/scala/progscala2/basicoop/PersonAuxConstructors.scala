// src/main/scala/progscala2/basicoop/PersonAuxConstructors.scala
package progscala2.basicoop

case class Address(street: String, city: String, state: String, zip: String) {

  def this(zip: String) =                                            // <1>
    this("[unknown]", Address.zipToCity(zip), Address.zipToState(zip), zip)
}

object Address {

  def zipToCity(zip: String)  = "Anytown"                            // <2>
  def zipToState(zip: String) = "CA"
}

case class Person(
  name: String, age: Option[Int], address: Option[Address]) {        // <3>

  def this(name: String) = this(name, None, None)                    // <4>

  def this(name: String, age: Int) = this(name, Some(age), None)

  def this(name: String, age: Int, address: Address) =
    this(name, Some(age), Some(address))

  def this(name: String, address: Address) = this(name, None, Some(address))
}
