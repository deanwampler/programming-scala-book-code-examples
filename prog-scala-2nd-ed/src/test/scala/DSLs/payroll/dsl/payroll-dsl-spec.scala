// code-examples/DSLs/payroll/dsl/payroll-dsl-spec.scala

package payroll.dsl
import org.scalatest.{ FunSpec, ShouldMatchers } 
 
import payroll._
import payroll.dsl.rules._
import payroll.Type2Money._

// Not very complete...
class PayrollSpec extends FunSpec with ShouldMatchers("Payroll") { 

  val payrollCalculator = rules { employee =>
    employee salary_for 2.weeks minus_deductions_for { gross =>
      federalIncomeTax            is  (25.  percent_of gross)
      stateIncomeTax              is  (5.   percent_of gross)
      insurancePremiums           are (500. in gross.currency)
      retirementFundContributions are (10.  percent_of gross)
    }
  }

  implicit def money2double(m: Money) = m.amount.doubleValue
  
  describe "Payroll calculation" {
    it "calculate the gross, net, and deductions for the pay period" {
      for (m <- 3 to 10) {
        val salary = Money(m * 10000.1)
        val buck = Employee(Name("Buck", "Trends"), salary)
        val expectedGross = salary / 26.
        val expectedDeductions = (expectedGross * .4) + Money(500)
        val expectedNet = expectedGross - expectedDeductions
        // For some reason, actual types as ScalaObject, unless cast.
        val actual = payrollCalculator(buck).asInstanceOf[Paycheck] 
        actual.gross must beCloseTo(expectedGross, Money(.001))
        actual.net must beCloseTo(expectedNet, Money(.001))
        actual.deductions must beCloseTo(expectedDeductions, Money(.001))
      }
    }
  }
}
