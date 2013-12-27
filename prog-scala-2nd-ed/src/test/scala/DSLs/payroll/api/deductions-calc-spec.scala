// src/test/scala/DSLs/payroll/api/deductions-calc-spec.scala

package dsls.payroll.api

import dsls.payroll.Type2Money._
import dsls.payroll.{Money, ArbitraryMoney, Employee, Name}
import scala.language.implicitConversions
import org.scalatest.PropSpec
import org.scalatest.prop.Checkers 
import org.scalacheck.Arbitrary._
import org.scalacheck.Prop._

/**
 * We'll use PropSpec for these tests to show a different ScalaTest style
 * especially useful for property testing.
 */
class DeductionsCalculatorSpec extends PropSpec with Checkers with ArbitraryMoney {

  val employee = Employee(Name("Buck", "Trends"), Money(80000))
  
  import DeductionsCalculator._

  property ("Federal Income Tax is calculated from the gross pay") {
    check((gross: Money) => federalIncomeTax(employee, gross) == gross * 0.25)
  }
  property ("State Income Tax is calculated from the gross pay") {
    check((gross: Money) => stateIncomeTax(employee, gross) == gross * 0.05)
  }
  property ("Insurance Premiums are not tied to the gross pay") {
    check((gross: Money) => insurancePremiums(employee, gross) == Money(500))
  }
  property ("Retirement Fund Contributions are calculated from the gross pay"){
    check((gross: Money) => 
      retirementFundContributions(employee, gross) == gross * 0.10)
  }
}
