// src/test/scala/DSLs/payroll/pcdsl/payroll-parser-comb-v1-spec.scala

package dsls.payroll.pcdsl
import dsls.payroll._
import dsls.payroll.Type2Money._
import scala.util.parsing.combinator._
import scala.language.implicitConversions
import org.scalatest.{ FunSpec, ShouldMatchers } 
 

class PayrollParserCombinatorsV1Spec extends FunSpec with ShouldMatchers { 
    
  describe ("PayrollParserCombinatorsV1") {
    it ("parse rules when there are no deductions") {
      val resultStr = """(("Buck Trends"~(2~weeks))~List())"""
      val input = """paycheck for employee "Buck Trends"
                     is salary for 2 weeks minus deductions for {}"""
      val p = new PayrollParserCombinatorsV1
      p.parseAll(p.paycheck, input) match {
        case p.Success(result, _) => result.toString shouldEqual resultStr
      }
    }

    it ("calculate the gross, net, and deductions for the pay period") {
      val resultStr = """(("Buck Trends"~(2~weeks))~List(25., 5., 500., 10.))"""
      val input = 
          """paycheck for employee "Buck Trends" 
             is salary for 2 weeks minus deductions for {
               federal income tax            is  25.  percent of gross,
               state income tax              is  5.   percent of gross,
               insurance premiums            are 500. in gross currency,
               retirement fund contributions are 10.  percent of gross
             }"""
      val p = new PayrollParserCombinatorsV1
      p.parseAll(p.paycheck, input) match {
        case p.Success(result, _) => result.toString shouldEqual resultStr
      }
    }
  }
}
