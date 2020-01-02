// src/main/scala/progscala3/basicoop/people/PersonEmployeeTraits.scala
package progscala3.basicoop.people                                   // <1>

trait PersonState {                                                  // <3>
  val name: String
  val age: Option[Int]
  val address: Option[Address]

  // Some common methods declared/defined here?
}

case class PersonWithTraits(                                         // <4>
  name: String,
  age: Option[Int] = None,
  address: Option[Address] = None) extends PersonState

trait EmployeeState {                                                // <5>
  val title: String
  val manager: Option[EmployeeWithTraits]
}

case class EmployeeWithTraits(                                       // <6>
  name: String,
  age: Option[Int] = None,                                           // <7>
  address: Option[Address] = None,
  title: String = "[unknown]",
  manager: Option[EmployeeWithTraits] = None)
extends PersonState with EmployeeState
