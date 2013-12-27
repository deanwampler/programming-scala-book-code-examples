// src/test/scala/DSLs/payroll/dsl/payroll-dsl-spec.scala

package dsls.payroll.dsl

import dsls.payroll._
import dsls.payroll.dsl.rules._
import dsls.payroll.Type2Money._
import dsls.payroll.{Money, ArbitraryMoney, Employee, Name}
import scala.language.implicitConversions
import org.scalatest.{ FunSpec, ShouldMatchers } 

// Not very complete...
class PayrollSpec extends FunSpec with ShouldMatchers { 

  val payrollCalculator = rules { employee =>
    employee salary_for 2.weeks minus_deductions_for { gross =>
      federalIncomeTax            is  (25.0  percent_of gross)
      stateIncomeTax              is  (5.0   percent_of gross)
      insurancePremiums           are (500.0 in gross.currency)
      retirementFundContributions are (10.0  percent_of gross)
    }
  }

  implicit def money2double(m: Money) = m.amount.doubleValue
  
  describe ("Payroll calculation") {
    it ("calculate the gross, net, and deductions for the pay period") {
      for (m <- 3 to 10) {
        val salary = Money(m * 10000.1)
        val buck = Employee(Name("Buck", "Trends"), salary)
        val expectedGross = salary / 26.0
        val expectedDeductions = (expectedGross * 0.4) + Money(500)
        val expectedNet = expectedGross - expectedDeductions
        // For some reason, actual types as ScalaObject, unless cast.
        val actual = payrollCalculator(buck).asInstanceOf[Paycheck] 
        actual.gross      shouldEqual expectedGross
        actual.net        shouldEqual expectedNet
        actual.deductions shouldEqual  expectedDeductions
      }
    }
  }
}
