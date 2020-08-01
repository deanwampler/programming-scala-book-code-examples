// src/main/scala/progscala3/dsls/payroll/Common.scala
package progscala3.dsls.payroll

object common:
  sealed trait Amount:                                          // <1>
    def amount: Double

  case class Percentage(amount: Double) extends Amount:
    override def toString = s"$amount%"

  case class Dollars(amount: Double) extends Amount:
    override def toString = s"$$$amount"

  def (amount: Double) percent: Percentage = Percentage(amount) // <2>
  def (amount: Double) dollars: Dollars = Dollars(amount)

  case class Deduction(name: String, amount: Amount):           // <3>
    override def toString = s"$name: $amount"

  case class Deductions(                                        // <4>
    name: String,
    divisorFromAnnualPay: Double = 1.0,
    deductions: Vector[Deduction] = Vector.empty):

    def gross(annualSalary: Double): Double =                   // <5>
      annualSalary / divisorFromAnnualPay

    def net(annualSalary: Double): Double =                     // <6>
      val g = gross(annualSalary)
      (deductions foldLeft g) {
        case (total, Deduction(deduction@_, amount)) => amount match
          case Percentage(value) => total - (g * value / 100.0)
          case Dollars(value) => total - value
      }

    override def toString =
      s"$name Deductions:" + deductions.mkString("\n  ", "\n  ", "")
