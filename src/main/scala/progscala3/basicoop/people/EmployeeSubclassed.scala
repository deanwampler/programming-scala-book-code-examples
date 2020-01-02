// src/main/scala/progscala3/basicoop/people/EmployeeSubclass.sc
package progscala3.basicoop.people

class PersonSubclassed(                                              // <1>
  val name: String,
  val age: Option[Int] = None,
  val address: Option[Address] = None)

class EmployeeSubclassed(                                            // <2>
  val name: String,
  val age: Option[Int] = None,
  val address: Option[Address] = None,
  val title: String = "[unknown]",                                   // <3>
  val manager: Option[EmployeeSubclassed] = None) 
    extends PersonSubclassed(name, age, address) {

  override def toString =                                            // <4>
    s"EmployeeSubclassed($name, $age, $address, $title, $manager)"
}
