// code-examples/DSLs/payroll/pcdsl/payroll-parser-comb-v1.scala

package dsls.payroll.pcdsl
import scala.util.parsing.combinator._
import dsls.payroll._
import dsls.payroll.Type2Money._

class PayrollParserCombinatorsV1 extends JavaTokenParsers {

  def paycheck = empl ~ gross ~ deduct

  def empl = "paycheck" ~> "for" ~> "employee" ~> employeeName

  def gross = "is" ~> "salary" ~> "for" ~> duration

  def deduct = "minus" ~> "deductions" ~> "for" ~> "{" ~> deductItems  <~ "}"

  // stringLiteral provided by JavaTokenParsers
  def employeeName = stringLiteral  
  
  // decimalNumber provided by JavaTokenParsers
  def duration = decimalNumber ~ weeksDays 

  def weeksDays = "weeks" | "week" | "days" | "day"

  def deductItems = repsep(deductItem, "," )
  
  def deductItem = deductKind ~> deductAmount 

  def deductKind = tax | insurance | retirement 

  def tax = fedState <~ "income" <~ "tax" 

  def fedState = "federal" | "state"

  def insurance = "insurance" ~> "premiums" 

  def retirement = "retirement" ~> "fund" ~> "contributions"

  def deductAmount = percentage | amount

  def percentage = toBe ~> doubleNumber <~ "percent" <~ "of" <~ "gross"

  def amount = toBe ~> doubleNumber <~ "in" <~ "gross" <~ "currency"

  def toBe = "is" | "are"

  // floatingPointNumber provided by JavaTokenParsers
  def doubleNumber = floatingPointNumber
}
