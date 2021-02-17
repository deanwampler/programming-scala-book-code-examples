// src/script/scala/progscala3/objectsystem/overrides/PayrollTemplateMethod.scala
// This example doesn't appear in the book. It's another example of using
// the Template Method Pattern (in netPay) to avoid overriding concrete methods.

case class Address(city: String, state: String, zip: String)
case class Employee(name: String, salary: Double, address: Address)

abstract class Payroll:
  final def netPay(employee: Employee): Double =                // <1>
    val fedTaxes   = calcFedTaxes(employee.salary)
    val stateTaxes = calcStateTaxes(employee.salary, employee.address)
    employee.salary - fedTaxes -stateTaxes

  protected def calcFedTaxes(salary: Double): Double            // <2>
  protected def calcStateTaxes(salary: Double, address: Address): Double

object Payroll2021 extends Payroll:
  val stateRate = Map(
    "YY" -> 0.03,
    "ZZ" -> 0.0)

  def calcFedTaxes(salary: Double): Double = salary * 0.25      // <3>
  def calcStateTaxes(salary: Double, address: Address): Double =
    salary * stateRate(address.state)

val tom  = Employee("Tom Jones", 100000.0, Address("MyTown", "YY", "98765"))
val jane = Employee("Jane Doe",  110000.0, Address("BigCity", "ZZ", "67890"))

assert(Payroll2021.netPay(tom)  == 72000.0)
assert(Payroll2021.netPay(jane) == 82500.0)
