// src/main/scala/progscala3/typesystem/PhantomTypesPayroll.scala
package progscala3.typesystem.payroll
import scala.language.implicitConversions

sealed trait Step                                          // <1>
trait PreTaxDeductions extends Step
trait PostTaxDeductions extends Step
trait Final extends Step

case class Employee(
    name: String,
    annualSalary: BigDecimal,
    taxRate: Double,                   // Assume one rate covers all taxes
    insurancePremiums: BigDecimal,
    _401kDeductionRate: Double,        // Pre-tax retirement plans in the US
    postTaxDeductions: BigDecimal):    // Other "after-tax" deductions
  override def toString: String = f"""
    |Employee: $name
    |  annual salary:         $$${annualSalary.toDouble}%.2f
    |  tax rate:              ${100*taxRate}%.2f%%
    |  per pay period deductions:
    |    insurance premiums:  $$${insurancePremiums.toDouble}%.2f
    |    401K deductions:     ${100*_401kDeductionRate}%.2f%%
    |    post tax deductions: $$${postTaxDeductions.toDouble}%.2f
    |""".stripMargin

case class Pay[S <: Step](                                 // <2>
    employee: Employee,
    grossPay: BigDecimal,         // This pay periods gross, before taxes
    netPay:   BigDecimal,         // This pay periods net, after taxes
    taxes:    BigDecimal = 0,
    preTaxDeductions: BigDecimal = 0,
    postTaxDeductions: BigDecimal = 0):
  override def toString: String = f"""
    |Pay for employee: ${employee.name}
    |  gross pay:            $$${grossPay.toDouble}%.2f
    |  net pay:              $$${netPay.toDouble}%.2f
    |  taxes:                $$${taxes.toDouble}%.2f
    |  pre-tax deductions:   $$${preTaxDeductions.toDouble}%.2f
    |  post-tax deductions:  $$${postTaxDeductions.toDouble}%.2f
    |""".stripMargin

object Payroll:
  def start(employee: Employee): Pay[PreTaxDeductions] =   // <3>
    val gross = employee.annualSalary / 12  // Compute monthly.
    Pay[PreTaxDeductions](employee, gross, gross)  // net == gross to start.

  def minusInsurance(pay: Pay[PreTaxDeductions]): Pay[PreTaxDeductions] =
    val newNet = pay.netPay - pay.employee.insurancePremiums
    val deductions = pay.preTaxDeductions + pay.employee.insurancePremiums
    pay.copy(netPay = newNet, preTaxDeductions = deductions)

  def minus401k(pay: Pay[PreTaxDeductions]): Pay[PreTaxDeductions] =
    val _401k = pay.grossPay * pay.employee._401kDeductionRate
    val newNet = pay.netPay - _401k
    val deductions = pay.preTaxDeductions + _401k
    pay.copy(netPay = newNet, preTaxDeductions = deductions)

  def minusTax(pay: Pay[PreTaxDeductions]): Pay[PostTaxDeductions] =
    val taxes = pay.netPay * pay.employee.taxRate
    val newNet = pay.netPay - taxes
    pay.copy(netPay = newNet, taxes = taxes)

  def minusFinalDeductions(pay: Pay[PostTaxDeductions]): Pay[Final] =
    val newNet = pay.netPay - pay.employee.postTaxDeductions
    val deductions = pay.employee.postTaxDeductions
    pay.copy(netPay = newNet, postTaxDeductions = deductions)

@main def TryPhantomTypes  =
  import Payroll._
  val e = Employee("Buck Trends", 100000.0, 0.25, 200, 0.10, 100.0)
  val pay1 = start(e)
  val pay2 = minus401k(pay1)                               // <4>
  val pay3 = minusInsurance(pay2)
  val pay4 = minusTax(pay3)
  val pay  = minusFinalDeductions(pay4)
  println(e); println(pay)
