// src/main/scala/progscala2/basicoop/PersonEmployeeTraits.scala
package progscala2.basicoop2                                         // <1>

case class Address(street: String, city: String, state: String, zip: String)

object Address {
  def apply(zip: String) =                                           // <2>
    new Address(
      "[unknown]", Address.zipToCity(zip), Address.zipToState(zip), zip)

  def zipToCity(zip: String)  = "Anytown"
  def zipToState(zip: String) = "CA"
}

trait PersonState {                                                  // <3>
  val name: String
  val age: Option[Int]
  val address: Option[Address]

  // Some common methods declared/defined here?
}

case class Person(                                                   // <4>
  name: String,
  age: Option[Int] = None,
  address: Option[Address] = None) extends PersonState

trait EmployeeState {                                                // <5>
  val title: String
  val manager: Option[Employee]
}

case class Employee(                                                 // <6>
  name: String,
  age: Option[Int] = None,                                           // <7>
  address: Option[Address] = None,
  title: String = "[unknown]",
  manager: Option[Employee] = None)
extends PersonState with EmployeeState
