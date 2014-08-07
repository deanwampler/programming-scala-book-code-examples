// src/main/scala/progscala2/dsls/payroll/api/deductions-calc.scala

package progscala2.dsls.payroll.api
import progscala2.dsls.payroll._
import progscala2.dsls.payroll.Type2Money._
import scala.language.implicitConversions

object DeductionsCalculator {
  def federalIncomeTax(empl: Employee, gross: Money) = gross * .25

  def stateIncomeTax(empl: Employee, gross: Money) = gross * .05

  def insurancePremiums(empl: Employee, gross: Money) = Money(500)

  def retirementFundContributions(empl: Employee, gross: Money) = gross * .10
}
