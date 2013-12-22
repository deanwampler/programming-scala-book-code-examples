// code-examples/DSLs/payroll/pcdsl/payroll-parser-comb-spec.scala

package dsls.payroll.pcdsl
import scala.util.parsing.combinator._
import org.scalatest.{ FunSpec, ShouldMatchers } 
import dsls.payroll._
import dsls.payroll.Type2Money._

// Doesn't test "sad path" scenarios...
class PayrollParserCombinatorsSpec extends FunSpec with ShouldMatchers { 

  val salary = Money(100000.1)  // for a full year
  val gross = salary / 26.0     // for two weeks
  val buck = Employee(Name("Buck", "Trends"), salary)
  val employees = Map(buck.name -> buck)

  implicit def money2double(m: Money) = m.amount.doubleValue
  
  describe ("PayrollParserCombinators") {
    it ("calculate the gross == net when there are no deductions") {
      val input = """paycheck for employee "Buck Trends"
                     is salary for 2 weeks minus deductions for {}"""
      val p = new PayrollParserCombinators(employees)
      p.parseAll(p.paycheck, input) match {
        case p.Success(Pair(employee, paycheck),_) =>
          employee mustEqual buck
          paycheck.gross must beCloseTo(gross, Money(0.001))
          paycheck.net must beCloseTo(gross, Money(0.001))
          // zero deductions?
          paycheck.deductions must beCloseTo(Money(0.0), Money(0.001))
        case x => fail(x.toString)
      }
    }
    
    it ("calculate the gross, net, and deductions for the pay period") {
      val input = 
        """paycheck for employee "Buck Trends" 
           is salary for 2 weeks minus deductions for {
             federal income tax            is  25.0  percent of gross,
             state income tax              is  5.0   percent of gross,
             insurance premiums            are 500.0 in gross currency,
             retirement fund contributions are 10.0  percent of gross
           }"""

      val p = new PayrollParserCombinators(employees)
      p.parseAll(p.paycheck, input) match {
        case p.Success(Pair(employee, paycheck),_) =>
          employee mustEqual buck
          val deductions = (gross * 0.4) + Money(500)
          val net = gross - deductions
          paycheck.gross must beCloseTo(gross, Money(0.001))
          paycheck.net must beCloseTo(net, Money(0.001))
          paycheck.deductions must beCloseTo(deductions, Money(0.001))
        case x => fail(x.toString)
      }
    }
  }
}
