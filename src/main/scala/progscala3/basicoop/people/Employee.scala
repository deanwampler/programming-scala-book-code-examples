// src/main/scala/progscala3/basicoop/people/Employee.scala
package progscala3.basicoop.people

case class Employee(
  name:    String,
  age:     Int,
  title:   String,
  manages: Set[Employee] = Set.empty) derives Eql
