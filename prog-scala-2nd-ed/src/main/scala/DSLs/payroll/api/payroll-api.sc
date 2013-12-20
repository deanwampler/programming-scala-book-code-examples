// code-examples/DSLs/payroll/api/payroll-api-script.scala

import dsls.payroll.api._
import dsls.payroll.api.DeductionsCalculator._
import dsls.payroll._
import dsls.payroll.Type2Money._

val buck = Employee(Name("Buck", "Trends"), Money(80000))
val jane = Employee(Name("Jane", "Doe"), Money(90000))

List(buck, jane).foreach { employee =>
  // Assume annual is based on 52 weeks.
  val biweeklyGross = employee.annualGrossSalary / 26.
  
  val deductions = federalIncomeTax(employee, biweeklyGross) + 
          stateIncomeTax(employee, biweeklyGross) + 
          insurancePremiums(employee, biweeklyGross) + 
          retirementFundContributions(employee, biweeklyGross)
  
  val check = Paycheck(biweeklyGross, biweeklyGross - deductions, deductions)
  
  format("%s %s: %s\n", employee.name.first, employee.name.last, check)
}
