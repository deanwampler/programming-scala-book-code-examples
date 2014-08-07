// src/main/scala/progscala2/implicits/phantom-types.scala

// A workflow for payroll calculations.

package progscala.implicits.payroll

sealed trait PreTaxDeductions
sealed trait PostTaxDeductions
sealed trait Final

// For simplicity, use Float for money. Not recommended...
case class Employee(
  name: String, 
  annualSalary: Float,
  taxRate: Float,  // For simplicity, just 1 rate covering all taxes.
  insurancePremiumsPerPayPeriod: Float,
  _401kDeductionRate: Float,  // A pretax, retirement savings plan in the USA.
  postTaxDeductions: Float)

case class Pay[Step](employee: Employee, netPay: Float)

object Payroll {
  // Biweekly paychecks. Assume exactly 52 weeks/year for simplicity.
  def start(employee: Employee): Pay[PreTaxDeductions] = 
    Pay[PreTaxDeductions](employee, employee.annualSalary / 26.0F)

  def minusInsurance(pay: Pay[PreTaxDeductions]): Pay[PreTaxDeductions] = {
    val newNet = pay.netPay - pay.employee.insurancePremiumsPerPayPeriod
    pay copy (netPay = newNet)
  }

  def minus401k(pay: Pay[PreTaxDeductions]): Pay[PreTaxDeductions] = {
    val newNet = pay.netPay - (pay.employee._401kDeductionRate * pay.netPay)
    pay copy (netPay = newNet)
  }

  def minusTax(pay: Pay[PreTaxDeductions]): Pay[PostTaxDeductions] = {
    val newNet = pay.netPay - (pay.employee.taxRate * pay.netPay)
    pay copy (netPay = newNet)
  }

  def minusFinalDeductions(pay: Pay[PostTaxDeductions]): Pay[Final] = {
    val newNet = pay.netPay - pay.employee.postTaxDeductions
    pay copy (netPay = newNet)
  }
}

object CalculatePayroll {
  def main(args: Array[String]) = {
    val e = Employee("Buck Trends", 100000.0F, 0.25F, 200F, 0.10F, 0.05F)
    val pay1 = Payroll start e
    // 401K and insurance can be calculated in either order.
    val pay2 = Payroll minus401k pay1
    val pay3 = Payroll minusInsurance pay2
    val pay4 = Payroll minusTax pay3
    val pay  = Payroll minusFinalDeductions pay4
    val twoWeekGross = e.annualSalary / 26.0F
    val twoWeekNet   = pay.netPay
    val percent      = (twoWeekNet / twoWeekGross) * 100
    println(s"For ${e.name}, the gross vs. net pay every 2 weeks is:")
    println(
      f"  $$${twoWeekGross}%.2f vs. $$${twoWeekNet}%.2f or ${percent}%.1f%%")
  }
}