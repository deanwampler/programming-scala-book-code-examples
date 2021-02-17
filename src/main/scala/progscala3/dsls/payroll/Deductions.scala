// src/main/scala/progscala3/dsls/payroll/Deductions.scala
package progscala3.dsls.payroll
import progscala3.contexts.accounting.*

sealed trait Deduction:                                         // <1>
  def name: String
  def amount(basis: Dollars): Dollars

case class PercentageDeduction(
    name: String, percentage: Percentage) extends Deduction:
  def amount(basis: Dollars): Dollars = basis * percentage
  override def toString = s"$name: $percentage"

case class DollarsDeduction(name: String, dollars: Dollars) extends Deduction:
  def amount(basis: Dollars): Dollars = dollars
  override def toString = s"$name: $dollars"

case class Deductions(                                          // <2>
  name: String,
  annualPayPeriods: Int = 1,
  deductions: Vector[Deduction] = Vector.empty):

  def gross(annualSalary: Dollars): Dollars =                   // <3>
    annualSalary / annualPayPeriods

  def net(annualSalary: Dollars): Dollars =                     // <4>
    val g = gross(annualSalary)
    deductions.foldLeft(g) {
      (total, deduction) => total - deduction.amount(g)
    }

  override def toString =
    s"$name Deductions:" + deductions.mkString("\n  ", "\n  ", "")
