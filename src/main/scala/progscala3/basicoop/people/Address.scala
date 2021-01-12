// src/main/scala/progscala3/basicoop/people/Address.scala
package progscala3.basicoop.people

case class Address(
  street: String, city: String, state: String, zip: ZipCode)

object Address:
  def apply(zip: ZipCode) = new Address(                      // <1>
    "[unknown]", Address.zipToCity(zip), Address.zipToState(zip), zip)

  def zipToCity(zip: ZipCode)  = s"Anytown-$zip"
  def zipToState(zip: ZipCode) = s"CA-$zip"
