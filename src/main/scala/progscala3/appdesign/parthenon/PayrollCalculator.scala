// src/main/scala/progscala3/appdesign/parthenon/PayrollCalculator.scala
package progscala3.appdesign.parthenon
import progscala3.dsls.payroll.parsercomb.dsl.PayrollParser
import progscala3.dsls.payroll.*
import progscala3.contexts.accounting.*

object PayrollCalculator:                                       // <1>
  val dsl = """biweekly {
      federal tax          %f  percent,
      state tax            %f  percent,
      insurance premiums   %f  dollars,
      retirement savings   %f  percent
    }"""

  case class Pay(                                               // <2>
    name: String, salary: Dollars, deductions: Deductions)

  def fromFile(inputFileName: String): Seq[Pay] =               // <3>
    val data = readData(inputFileName)
    for
      (name, salary, ruleString) <- data
    yield Pay(name, salary, toDeductions(ruleString))

  case class BadInput(message: String, input: String)
    extends RuntimeException(s"Bad input data, $message: $input")

  private type Record = (String, Dollars, String)               // <4>

  private def readData(inputFileName: String): Seq[Record] =
    for
      line <- scala.io.Source.fromFile(inputFileName).getLines.toVector
      if line.matches("\\s*#.*") == false    // skip comments
    yield toRule(line)

  private def toRule(line: String): Record =                    // <5>
    line.split("""\s*,\s.*""") match
      case Array(name, salary, fedTax, stateTax, insurance, retirement) =>
        val ruleString = dsl.format(
          fedTax.toDouble, stateTax.toDouble,
          insurance.toDouble, retirement.toDouble)
        (name, Dollars(salary.toDouble), ruleString)
      case array => throw BadInput("expected six fields", line)

  private val parser = PayrollParser()                          // <6>
  private def toDeductions(rule: String): Deductions =
    parser.parseAll(parser.biweekly, rule).get
