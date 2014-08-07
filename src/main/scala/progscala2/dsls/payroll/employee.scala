// src/main/scala/progscala2/dsls/payroll/employee.scala

package progscala2.dsls.payroll

case class Name(first: String, last: String)

case class Employee(name: Name, annualGrossSalary: Money)

