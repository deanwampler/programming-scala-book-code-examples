// src/main/scala/progscala3/dsls/payroll/parsercomb/DSL.scala
package progscala3.dsls.payroll.parsercomb
import scala.util.parsing.combinator.*
import progscala3.dsls.payroll.*
import progscala3.contexts.accounting.*

@main def TryPayroll =                                               // <1>
  import dsl.PayrollParser
  val input = """biweekly {
    federal tax          20.0  percent,
    state tax            3.0   percent,
    insurance premiums   250.0 dollars,
    retirement savings   15.0  percent
  }"""
  val parser = PayrollParser()
  val biweeklyDeductions = parser.parseAll(parser.biweekly, input).get

  println(biweeklyDeductions)
  val annualGross = Dollars(100000.0)
  val gross = biweeklyDeductions.gross(annualGross)
  val net   = biweeklyDeductions.net(annualGross)
  print(f"Biweekly pay (annual: $annualGross): ")
  println(f"Gross: $gross, Net: $net")
end TryPayroll

object dsl:
  class PayrollParser extends JavaTokenParsers:                      // <2>

    /** @return Parser[(Deductions)] */
    def biweekly = "biweekly" ~> "{" ~> deductions <~ "}" ^^ { ds =>
      Deductions("Biweekly", 26, ds)                                 // <3>
    }

    /** @return Parser[Vector[Deduction]] */
    def deductions = repsep(deduction, ",") ^^ { ds =>               // <4>
      ds.toVector
    }

    /** @return Parser[Deduction] .*/
    def deduction =                                                  // <5>
      federal_tax | state_tax | insurance | retirement

    /** @return Parser[Deduction] */
    def federal_tax = parsePercentageDeduction("federal", "tax")     // <6>
    def state_tax   = parsePercentageDeduction("state", "tax")
    def retirement  = parsePercentageDeduction("retirement", "savings")
    def insurance   = parseDollarsDeduction("insurance", "premiums")

    private def parsePercentageDeduction(word1: String, word2: String) =
      word1 ~> word2 ~> percentage ^^ {
        percentage => PercentageDeduction(s"${word1} ${word2}", percentage)
      }
    private def parseDollarsDeduction(word1: String, word2: String) =
      word1 ~> word2 ~> dollars ^^ {
        dollars => DollarsDeduction(s"${word1} ${word2}", dollars)
      }

    /** @return Parser[Dollars] */
    def dollars = doubleNumber <~ "dollars" ^^ { d => Dollars(d) }   // <7>

    /** @return Parser[Percentage] */
    def percentage = doubleNumber <~ "percent" ^^ { d => Percentage(d) }

    def doubleNumber = floatingPointNumber ^^ (_.toDouble)
  end PayrollParser
end dsl
