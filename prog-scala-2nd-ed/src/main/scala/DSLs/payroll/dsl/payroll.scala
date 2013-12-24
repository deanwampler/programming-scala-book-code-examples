// code-examples/DSLs/payroll/dsl/payroll.scala

package dsls.payroll.dsl
import dsls.payroll._
import scala.language.implicitConversions

object rules {
  def apply(rules: Employee => Paycheck) = new PayrollBuilderRules(rules)

  implicit def int2Duration(i: Int) = Duration(i)

  implicit def employee2GrossPayBuilder(e: Employee) =
    new GrossPayBuilder(e)

  implicit def grossPayBuilder2DeductionsBuilder(b: GrossPayBuilder)
    = new DeductionsBuilder(b)

  implicit def double2DeductionsBuilderDeductionHelper(d: Double) =
    new DeductionsBuilderDeductionHelper(d)
}

import rules._

class PayrollException(message: String, cause: Throwable)
  extends RuntimeException(message, cause)

protected[dsl] class PayrollBuilderRules(rules: Employee => Paycheck) {
  def apply(employee: Employee) = {
    try {
      rules(employee)
    } catch {
      case th: Throwable => new PayrollException(
        "Failed to process payroll for employee: " + employee, th
      )
    }
  }
}

import dsls.payroll.Type2Money._

protected[dsl] class GrossPayBuilder(val employee: Employee) {
  var gross: Money = 0

  def salary_for(days: Int) = {
    gross += dailyGrossSalary(employee.annualGrossSalary) * days
    this
  }

  // Assume 260 working days: 52 weeks (including vacation) * 5 days/week.
  def weeklyGrossSalary(annual: Money) = annual / 52.0
  def dailyGrossSalary(annual: Money)  = annual / 260.0
}

protected[dsl] class DeductionsBuilder(gpb: GrossPayBuilder) {
  val employee = gpb.employee
  var paycheck: Paycheck = new Paycheck(gpb.gross, gpb.gross, 0)

  def currency = this

  def minus_deductions_for(deductionRules: DeductionsBuilder => Unit) = {
    deductionRules(this)
    paycheck
  }

  def addDeductions(amount: Money) =
    paycheck = paycheck plusDeductions amount

  def addDeductionsPercentageOfGross(percentage: Double) = {
    val amount = paycheck.gross * (percentage/100.0)
    addDeductions(amount)
  }
}

class DeductionCalculator {
  def is(builder: DeductionsBuilder) = apply(builder)
  def are(builder: DeductionsBuilder) = apply(builder)

  def apply(builder: DeductionsBuilder) = {}
}

object federalIncomeTax extends DeductionCalculator
object stateIncomeTax extends DeductionCalculator
object insurancePremiums extends DeductionCalculator
object retirementFundContributions extends DeductionCalculator

protected[dsl] class DeductionsBuilderDeductionHelper(val factor: Double) {
  def in (builder: DeductionsBuilder) = {
    builder addDeductions Money(factor)
    builder
  }
  def percent_of (builder: DeductionsBuilder) = {
    builder addDeductionsPercentageOfGross factor
    builder
  }
}
