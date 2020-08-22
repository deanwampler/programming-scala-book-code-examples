// src/script/scala/progscala3/basicoop/people/EmployeeSubclass.scala

case class Person(val name: String)

class Employee(name: String, val salary: Float) extends Person(name)
