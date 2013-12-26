// code-examples/DSLs/payroll/employee.scala

package dsls.payroll

case class Name(first: String, last: String)

case class Employee(name: Name, annualGrossSalary: Money)

