// src/test/scala/DSLs/payroll/pcdsl/payroll-parser-comb-complete-spec.scala
package dsls.payroll.pcdsl

import dsls.payroll._
import dsls.payroll.Type2Money._
import scala.util.parsing.combinator._
import scala.language.implicitConversions
import org.scalatest.{ FunSpec, ShouldMatchers }  

/**
 * This is more complete spec than "PayrollParserCombinatorsSpec", which 
 * exercises most of the productions individually, at least with good input
 * strings. That is, we focus on "happy path" scenarios, with a few exceptions.
 * The spec should really exercise "sad path" scenarios more heavily and use 
 * them to drive better error handling and recovery.
 */
class PayrollParserCombinatorsCompleteSpec extends FunSpec with ShouldMatchers{

  implicit def money2double(m: Money) = m.amount.doubleValue
  
  // Start with examples for pieces of the DSL:
  
  describe ("PayrollParserCombinators") {
    describe ("duration") {
      it ("matches an integer followed by week, weeks, day, days") {
        val p = new PayrollParserCombinators(Map())
        List("1 week", "1 weeks", "5 day", "5 days").foreach { str =>
          p.parseAll(p.duration, str) match {
            case p.Success(d,_) => d shouldEqual 5
            case x => fail(x.toString)
          }
        }
      }
    }
    
    describe ("amount") {
      it ("matches are X in gross currency") {
        val p = new PayrollParserCombinators(Map())
        List("20", "20.0").foreach { m => 
          p.parseAll(p.amount, s"are $m in gross currency") match {
            case p.Success(m,_) => m shouldEqual Money(20.0)
            case x => fail(x.toString)
          }
        }
      }
    }
        
    describe ("percentage") {
      it ("matches X percent of gross") {
        val p = new PayrollParserCombinators(Map())
        p.grossAmount = Money(10000.0)
        List("20", "20.", "20.0").foreach { m => 
          List("is", "are").foreach { preposition =>
            val input = s"$preposition $m percent of gross"
            p.parseAll(p.percentage, input) match {
              case p.Success(m,_) => m shouldEqual Money(2000.0)
              case x => fail(x.toString)
            }
          }
        }
      }
    }
        
    describe ("empl") {
      val name = Name("Buck", "Trends")
      val buck = Employee(name, Money(10000.0))
      val employees = Map(name -> buck)

      it ("matches an employee name to an item in the employee map") {
        val p = new PayrollParserCombinators(employees)
        val input = "paycheck for employee \"Buck Trends\""
        p.parseAll(p.empl, input) match {
          case p.Success(e,_) => e shouldEqual buck
          case x => fail(x.toString)
        }
      }
      it ("throws an exception if the employee isn't known") {
        val p = new PayrollParserCombinators(employees)
        val input = "paycheck for employee \"John Doe\""
        intercept[UnknownEmployee] { p.parseAll(p.empl, input) }
      }
    }
        
    describe ("deductItem") {
      it ("matches tax, insurance, and retirement fund deductions " +
          "specified as percentages or values.") {
        val p = new PayrollParserCombinators(Map())
        p.grossAmount = Money(10000.0)
        List("federal income tax", 
           "state income tax",
           "insurance premiums", 
           "retirement fund contributions").foreach {prefix =>
          List(" is 20.0 percent of gross", 
             " are 20.0 percent of gross", 
             " is 2000.0 in gross currency",
             " are 2000.0 in gross currency").foreach {suffix =>
            p.parseAll(p.deductItem, prefix + suffix) match {
              case p.Success(m,_) => m shouldEqual Money(2000.0)
              case x => fail(x.toString)
            }
          }
        }
      }
    }
        
    describe ("deduct") {
      it ("matches a set of deduction items") {
        val p = new PayrollParserCombinators(Map())
        p.grossAmount = Money(10000.0)
        val input = """minus deductions for {
            federal income tax      is  25.0  percent of gross,
            state income tax        is  5.0   percent of gross,
            insurance premiums      are 500.0 in gross currency,
            retirement fund contributions are 10.0  percent of gross
        }
        """
        p.parseAll(p.deduct, input) match {
          case p.Success(m,_) => m shouldEqual Money(4500.0)
          case x => fail(x.toString)
        }
      }
    }
        
    // Now try the whole DSL:
    
    describe ("The whole DSL") {
      it ("calculates the gross == net when there are no deductions") {
        val salary = Money(10000.0)
        val buck = Employee(Name("Buck", "Trends"), salary)
        val expectedGross = salary / 26.0
        val employees = Map(buck.name -> buck)
        val input = """paycheck for employee "Buck Trends" is
         salary for 2 weeks minus deductions for {}"""      
        val p = new PayrollParserCombinators(employees)
        p.parseAll(p.paycheck, input) match {
          case p.Success(Pair(employee, paycheck),_) =>
            employee            shouldEqual buck
            paycheck.gross      shouldEqual expectedGross
            paycheck.net        shouldEqual expectedGross
            paycheck.deductions shouldEqual Money(0.0)
          case x => fail(x.toString)
        }
      }
      
      it ("calculates the gross, net, and deductions for the pay period") {
        val input = """paycheck for employee "Buck Trends" is 
            salary for 2 weeks minus deductions for {
            federal income tax      is  25.0  percent of gross,
            state income tax        is  5.0   percent of gross,
            insurance premiums      are 500.0 in gross currency,
            retirement fund contributions are 10.0  percent of gross
          }
        """

        for (m <- 3 to 10) {
          val salary = Money(m * 10000.1)
          val buck = Employee(Name("Buck", "Trends"), salary)
          val expectedGross = salary / 26.0
          val expectedDeductions = (expectedGross * 0.4) + Money(500)
          val expectedNet = expectedGross - expectedDeductions
          val employees = Map(buck.name -> buck)
          val p = new PayrollParserCombinators(employees)
          p.parseAll(p.paycheck, input) match {
            case p.Success(Pair(employee, paycheck),_) =>
              employee            shouldEqual buck
              paycheck.gross      shouldEqual expectedGross
              paycheck.net        shouldEqual expectedNet
              paycheck.deductions shouldEqual expectedDeductions
            case _ => fail
          }
        }
      }
    }
  }
}
