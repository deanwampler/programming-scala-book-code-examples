// code-examples/DSLs/payroll/api/deductions-calc-spec.scala

package payroll.api
import org.specs2.mutable._ 
import org.scalacheck._
import org.scalacheck.Prop._
import payroll.Type2Money._

object DeductionsCalculatorSpec extends Specification 
        with ScalaCheck with ArbitraryMoney { 

  val employee = Employee(Name("Buck", "Trends"), Money(80000))
  
  "federalIncomeTax" verifies { 
    (gross: Money) => 
      DeductionsCalculator.federalIncomeTax(
        employee, gross) == gross * .25
  }
  "stateIncomeTax" verifies { 
    (gross: Money) => 
      DeductionsCalculator.stateIncomeTax(
        employee, gross) == gross * .05
  }
  "insurancePremiums" verifies { 
    (gross: Money) => 
      DeductionsCalculator.insurancePremiums(
        employee, gross) == Money(500)
  }
  "retirementFundContributions" verifies { 
    (gross: Money) => 
      DeductionsCalculator.retirementFundContributions(
        employee, gross) == gross * .10
  }
}
