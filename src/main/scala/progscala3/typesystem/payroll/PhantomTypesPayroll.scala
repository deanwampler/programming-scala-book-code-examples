// src/main/scala/progscala3/typesystem/payroll/PhantomTypesPayroll.scala
package progscala3.typesystem.payroll
import progscala3.contexts.accounting.*                    // <1>

sealed trait Step                                          // <2>
trait PreTaxDeductions extends Step
trait PostTaxDeductions extends Step
trait Final extends Step

case class Employee(
    name: String,
    annualSalary: Dollars,
    taxRate: Percentage,             // Assume one rate covers all taxes
    insurancePremiums: Dollars,
    _401kDeductionRate: Percentage,  // Pre-tax retirement plans in the US
    postTaxDeductions: Dollars):     // Other "after-tax" deductions
  override def toString: String = f"""
    |Employee: $name
    |  annual salary:         $annualSalary
    |  tax rate:              $taxRate
    |  per pay period deductions:
    |    insurance premiums:  $insurancePremiums
    |    401K deductions:     $_401kDeductionRate
    |    post tax deductions: $postTaxDeductions
    |""".stripMargin

case class Pay[S <: Step](                                 // <3>
    employee: Employee,
    grossPay: Dollars,         // This pay period's gross, before taxes
    netPay:   Dollars,         // This pay period's net, after taxes
    taxes:    Dollars = Dollars(0.0),
    preTaxDeductions: Dollars = Dollars(0.0),
    postTaxDeductions: Dollars = Dollars(0.0)):
  override def toString: String = f"""
    |Pay for employee: ${employee.name}
    |  gross pay:            $grossPay
    |  net pay:              $netPay
    |  taxes:                $taxes
    |  pre-tax deductions:   $preTaxDeductions
    |  post-tax deductions:  $postTaxDeductions
    |""".stripMargin

object Payroll:
  def start(employee: Employee): Pay[PreTaxDeductions] =   // <4>
    val gross = employee.annualSalary / 12         // Compute monthly.
    Pay[PreTaxDeductions](employee, gross, gross)  // net == gross to start.

  def deductInsurance(pay: Pay[PreTaxDeductions]): Pay[PreTaxDeductions] =
    val newNet = pay.netPay - pay.employee.insurancePremiums
    val deductions = pay.preTaxDeductions + pay.employee.insurancePremiums
    pay.copy(netPay = newNet, preTaxDeductions = deductions)

  def deduct401k(pay: Pay[PreTaxDeductions]): Pay[PreTaxDeductions] =
    val _401k = pay.grossPay * pay.employee._401kDeductionRate
    val newNet = pay.netPay - _401k
    val deductions = pay.preTaxDeductions + _401k
    pay.copy(netPay = newNet, preTaxDeductions = deductions)

  def deductTax(pay: Pay[PreTaxDeductions]): Pay[PostTaxDeductions] =
    val taxes = pay.netPay * pay.employee.taxRate
    val newNet = pay.netPay - taxes
    pay.copy(netPay = newNet, taxes = taxes)

  def deductFinalDeductions(pay: Pay[PostTaxDeductions]): Pay[Final] =
    val newNet = pay.netPay - pay.employee.postTaxDeductions
    val deductions = pay.employee.postTaxDeductions
    pay.copy(netPay = newNet, postTaxDeductions = deductions)
end Payroll

@main def TryPhantomTypes  =
  import Payroll.*
  val e = Employee("Buck Trends", Dollars(100000.0), Percentage(25.0),
    Dollars(200), Percentage(10.0), Dollars(100.0))
  val pay1 = start(e)
  val pay2 = deduct401k(pay1)                              // <5>
  val pay3 = deductInsurance(pay2)
  val pay4 = deductTax(pay3)
  val pay  = deductFinalDeductions(pay4)
  println(e); println(pay)
