// src/main/scala/BasicOOP/person-aux-constructors3.sc

import oop.Address

val a1 = new Address("1 Scala Lane", "Anytown", "CA", "98765")
val a2 = new Address("98765")

// NOTE: Use :paste in the REPL for the case class and companion object
case class Person3(
  name: String, 
  age: Option[Int] = None, 
  address: Option[Address] = None) 

object Person3 {

  def apply(name: String): Person3 = new Person3(name)     // <1>  

  def apply(name: String, age: Int): Person3 = new Person3(name, Some(age))
  
  def apply(name: String, age: Int, address: Address): Person3 = 
    new Person3(name, Some(age), Some(address))
  
  def apply(name: String, address: Address): Person3 = 
    new Person3(name, address = Some(address))  
}

Person3("Buck Trends1")                                    // <2>
// Result: Person3 = Person3(Buck Trends1,None,None)

Person3("Buck Trends2", Some(20), Some(a1))                // <2>
// Result: Person3 = Person3(Buck Trends2,Some(20),
//           Some(Address(1 Scala Lane,Anytown,CA,98765)))

Person3("Buck Trends2", 20, a1)
// Result: Person3 = Person3(Buck Trends2,Some(20),
//           Some(Address(1 Scala Lane,Anytown,CA,98765)))

Person3("Buck Trends2", Some(20))                          // <2>
// Result: Person3 = Person3(Buck Trends2,Some(20),None)

Person3("Buck Trends2", 20)
// Result: Person3 = Person3(Buck Trends2,Some(20),None)

Person3("Buck Trends2", address = Some(a2))                // <2>
// Result: Person3 = Person3(Buck Trends2,None,
//           Some(Address(<unknown>,Anytown,CA,98765)))

Person3("Buck Trends2", address = a2)
// Result: Person3 = Person3(Buck Trends2,None,
//           Some(Address(<unknown>,Anytown,CA,98765)))