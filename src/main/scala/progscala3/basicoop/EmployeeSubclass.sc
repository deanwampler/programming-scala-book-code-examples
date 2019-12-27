// src/main/scala/progscala3/basicoop/EmployeeSubclass.sc
import progscala3.basicoop.Address

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
println(ceo)
// Result: Employee(Joe CEO, None, None, CEO, None)

println(new Employee("Buck Trends1"))
// Result: Employee(Buck Trends1, None, None, [unknown], None)

println(new Employee("Buck Trends2", Some(20), Some(a1)))
// Result:  Employee(Buck Trends2, Some(20),
//            Some(Address(1 Scala Lane,Anytown,CA,98765)), [unknown], None)

println(new Employee("Buck Trends3", Some(20), Some(a1), "Zombie Dev"))
// Result:  Employee(Buck Trends3, Some(20),
//            Some(Address(1 Scala Lane,Anytown,CA,98765)), Zombie Dev, None)

println(new Employee("Minion", Some(20), Some(a1), "Zombie Dev", Some(ceo)))
// Result:  Employee(Minion, Some(20),
//            Some(Address(1 Scala Lane,Anytown,CA,98765)), Zombie Dev,
//            Some(Employee(Joe CEO, None, None, CEO, None)))
