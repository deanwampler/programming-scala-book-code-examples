// src/main/scala/progscala3/dsls/payroll/Deductions.scala
package progscala3.dsls.payroll
import scala.annotation.targetName

case class Deduction(name: String, amount: Amount):             // <1>
  override def toString = s"$name: $amount"

case class Deductions(                                          // <2>
  name: String,
  divisorFromAnnualPay: Double = 1.0,
  deductions: Vector[Deduction] = Vector.empty):

  def gross(annualSalary: Double): Double =                     // <3>
    annualSalary / divisorFromAnnualPay

  def net(annualSalary: Double): Double =                       // <4>
    val g = gross(annualSalary)
    (deductions foldLeft g) {
      case (total, Deduction(_, amount)) => amount match
        case Percentage(value) => total - (g * value / 100.0)
        case Dollars(value) => total - value
    }

  override def toString =
    s"$name Deductions:" + deductions.mkString("\n  ", "\n  ", "")
