// src/main/scala/progscala2/basicoop/EmployeeSubclass.sc
import progscala2.basicoop.Address

case class Person(    // This was Person2 previously, now renamed.
  name: String,
  age: Option[Int] = None,
  address: Option[Address] = None)

class Employee(                                                      // <1>
  name: String,
  age: Option[Int] = None,
  address: Option[Address] = None,
  val title: String = "[unknown]",                                   // <2>
  val manager: Option[Employee] = None) extends Person(name, age, address) {

  override def toString =                                            // <3>
    s"Employee($name, $age, $address, $title, $manager)"
}

val a1 = new Address("1 Scala Lane", "Anytown", "CA", "98765")
val a2 = new Address("98765")

val ceo = new Employee("Joe CEO", title = "CEO")
// Result: Employee(Joe CEO, None, None, CEO, None)

new Employee("Buck Trends1")
// Result: Employee(Buck Trends1, None, None, [unknown], None)

new Employee("Buck Trends2", Some(20), Some(a1))
// Result:  Employee(Buck Trends2, Some(20),
//            Some(Address(1 Scala Lane,Anytown,CA,98765)), [unknown], None)

new Employee("Buck Trends3", Some(20), Some(a1), "Zombie Dev")
// Result:  Employee(Buck Trends3, Some(20),
//            Some(Address(1 Scala Lane,Anytown,CA,98765)), Zombie Dev, None)

new Employee("Buck Trends4", Some(20), Some(a1), "Zombie Dev", Some(ceo))
// Result:  Employee(Buck Trends4, Some(20),
//            Some(Address(1 Scala Lane,Anytown,CA,98765)), Zombie Dev,
//            Some(Employee(Joe CEO, None, None, CEO, None)))
