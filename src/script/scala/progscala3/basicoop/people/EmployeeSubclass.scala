// src/script/scala/progscala3/basicoop/people/EmployeeSubclass.scala

class Person(name: String, age: Int)
class Employee(name: String, age: Int, salary: Float) extends Person(name, age)
class Manager(name: String, age: Int, salary: Float, minions: Seq[Employee])
  extends Employee(name, age, salary)
