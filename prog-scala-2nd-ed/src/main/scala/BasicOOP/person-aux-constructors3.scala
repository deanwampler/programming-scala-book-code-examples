// src/main/scala/BasicOOP/person-aux-constructors3.sc

package oop

case class Person3(
  name: String, 
  age: Option[Int] = None, 
  address: Option[Address] = None) 

object Person3 {

  // Because we are overloading a normal method (as opposed to constructors),
  // we must specify the return type annotation, Person3 in this case.
  def apply(name: String): Person3 = new Person3(name)

  def apply(name: String, age: Int): Person3 = new Person3(name, Some(age))
  
  def apply(name: String, age: Int, address: Address): Person3 = 
    new Person3(name, Some(age), Some(address))
  
  def apply(name: String, address: Address): Person3 = 
    new Person3(name, address = Some(address))  
}
