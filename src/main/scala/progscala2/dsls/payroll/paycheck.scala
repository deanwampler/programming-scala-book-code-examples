// src/main/scala/progscala2/dsls/payroll/paycheck.scala

package progscala2.dsls.payroll
import scala.language.implicitConversions

/** We're ignoring invalid (?) cases like a negative net 
 *  when deductions exceed the gross.
 */
case class Paycheck(gross: Money, net: Money, deductions: Money) {

  def plusGross (m: Money)      = Paycheck(gross + m, net + m, deductions)
  def plusDeductions (m: Money) = Paycheck(gross,     net - m, deductions + m)
}

