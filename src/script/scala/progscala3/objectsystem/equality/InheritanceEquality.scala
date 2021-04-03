// tag::definitions[]
// src/script/scala/progscala3/objectsystem/equality/InheritanceEquality.scala

class Employee(val name: String, val annualSalary: Double)
class Manager(name: String, annualSalary: Double, val minions: Seq[Employee])
  extends Employee(name, annualSalary)

val e1  = new Employee("Buck Trends", 50000.0)
val e1b = new Employee("Buck Trends", 50000.0)
val e2  = new Employee("Jane Doe", 50000.0)
val m1  = new Manager("Jane Doe", 50000.0, Seq(e1, e2))
val all = Seq(e1, e1b, e2, m1)
// end::definitions[]

// tag::equals1[]
assert((e1 == e1)  == true)
assert((e1 == e1b) == false)   // Different references, so == returns false.
assert((e1 == e2)  == false)
assert((e2 == m1)  == false)
// end::equals1[]

// tag::equals2[]
val same = all.filter(e => e2 == e)
// end::equals2[]
