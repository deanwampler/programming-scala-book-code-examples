// code-examples/DSLs/payroll/pcdsl/payroll-parser-comb.scala

package dsls.payroll.pcdsl
import scala.util.parsing.combinator._
import dsls.payroll._
import dsls.payroll.Type2Money._

class UnknownEmployee(name: Name) extends RuntimeException(name.toString)

class PayrollParserCombinators(val employees: Map[Name, Employee]) 
  extends JavaTokenParsers {
  
  var currentEmployee: Employee = null
  var grossAmount: Money = Money(0)
  
  /** @return Parser[(Employee, Paycheck)] */
  def paycheck = empl ~ gross ~ deduct ^^ {
    case e ~ g ~ d => (e, Paycheck(g, g-d, d))
  }

  /** @return Parser[Employee] */
  def empl = "paycheck" ~> "for" ~> "employee" ~> employeeName ^^ { name =>
    val names = name.substring(1, name.length-1).split(" ") // remove ""
    val n = Name(names(0), names(1));
    if (! employees.contains(n))
      throw new UnknownEmployee(n)
    currentEmployee = employees(n)
    currentEmployee
  }

  /** @return Parser[Money] */
  def gross = "is" ~> "salary" ~> "for" ~> duration ^^ { dur =>
    grossAmount = salaryForDays(dur)
    grossAmount
  }

  def deduct = "minus" ~> "deductions" ~> "for" ~> "{" ~> deductItems  <~ "}"
      
  /** 
   * "stringLiteral" provided by JavaTokenParsers
   * @return Parser[String]
   */
  def employeeName = stringLiteral  

  /** 
   * "decimalNumber" provided by JavaTokenParsers
   * @return Parser[Int]
   */
  def duration = decimalNumber ~ weeksDays ^^ { 
    case n ~ factor => n.toInt * factor
  }
  
  def weeksDays = weeks | days
  
  def weeks = "weeks?".r ^^ { _ => 5 }

  def days = "days?".r ^^ { _ => 1 }
  
  /** @return Parser[Money] */
  def deductItems = repsep(deductItem, ",") ^^ { items =>
    items.foldLeft(Money(0)) {_ + _}
  }

  /** @return Parser[Money] */
  def deductItem = deductKind ~> deductAmount 

  def deductKind = tax | insurance | retirement 

  def tax = fedState <~ "income" <~ "tax" 

  def fedState = "federal" | "state"

  def insurance = "insurance" ~> "premiums" 

  def retirement = "retirement" ~> "fund" ~> "contributions"

  def deductAmount = percentage | amount
  
  /** @return Parser[Money] */
  def percentage = toBe ~> doubleNumber <~ "percent" <~ "of" <~ "gross"  ^^ {
    percentage => grossAmount * (percentage/100.0)
  }
      
  def amount = toBe ~> doubleNumber <~ "in" <~ "gross" <~ "currency" ^^ {
    Money(_)
  }

  def toBe = "is" | "are"
  
  def doubleNumber = floatingPointNumber ^^ { _.toDouble }

  // Support method. Assume 260 (52 * 5) paid work days/year    
  def salaryForDays(days: Int) = 
      (currentEmployee.annualGrossSalary / 260.0) * days
}
