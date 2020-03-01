// src/main/scala/progscala3/objectsystem/overrides/payroll-template-method.sc

case class Address(city: String, state: String, zip: String)
case class Employee(name: String, salary: Double, address: Address)

abstract class Payroll {
  def netPay(employee: Employee): Double = {                         // <1>
    val fedTaxes   = calcFedTaxes(employee.salary)
    val stateTaxes = calcStateTaxes(employee.salary, employee.address)
    employee.salary - fedTaxes -stateTaxes
  }

  def calcFedTaxes(salary: Double): Double                           // <2>
  def calcStateTaxes(salary: Double, address: Address): Double       // <3>
}

object Payroll2020 extends Payroll {
  val stateRate = Map(
    "XX" -> 0.05,
    "YY" -> 0.03,
    "ZZ" -> 0.0)

  def calcFedTaxes(salary: Double): Double = salary * 0.25           // <4>
  def calcStateTaxes(salary: Double, address: Address): Double = {
    // Assume the address.state is valid; it's found in the map!
    salary * stateRate(address.state)
  }
}

val tom  = Employee("Tom Jones", 100000.0, Address("MyTown", "XX", "12345"))
val jane = Employee("Jane Doe",  110000.0, Address("BigCity", "YY", "67890"))

assert(Payroll2020.netPay(tom)  == 70000.0)
assert(Payroll2020.netPay(jane) == 79200.0)
