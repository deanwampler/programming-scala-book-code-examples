// src/test/scala/DSLs/payroll/pcdsl/payroll-parser-comb-spec.scala

package dsls.payroll.pcdsl

import dsls.payroll._
import dsls.payroll.Type2Money._
import scala.util.parsing.combinator._
import scala.language.implicitConversions 
import org.scalatest.{ FunSpec, ShouldMatchers } 

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
          employee shouldEqual buck
          paycheck.gross shouldEqual gross
          paycheck.net   shouldEqual gross
          // zero deductions?
          paycheck.deductions shouldEqual Money(0.0)
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
          employee shouldEqual buck
          val deductions = (gross * .4) + Money(500)
          val net = gross - deductions
          paycheck.gross      shouldEqual gross
          paycheck.net        shouldEqual net
          paycheck.deductions shouldEqual deductions
        case x => fail(x.toString)
      }
    }
  }
}
