// src/main/scala/progscala3/dsls/payroll/parsercomb/DSL.scala
package progscala3.dsls.payroll.parsercomb
import scala.util.parsing.combinator._
import progscala3.dsls.payroll._
import scala.language.implicitConversions

@main def TryPayroll =                                          // <1>
  import dsl.PayrollParser
  val input = """biweekly {
    federal tax          20.0  percent,
    state tax            3.0   percent,
    insurance premiums   250.0 dollars,
    retirement savings   15.0  percent
  }"""
  val parser = new PayrollParser
  val biweeklyDeductions = parser.parseAll(parser.biweekly, input).get

  println(biweeklyDeductions)
  val annualGross = 100000.0
  val gross = biweeklyDeductions.gross(annualGross)
  val net   = biweeklyDeductions.net(annualGross)
  print(f"Biweekly pay (annual: $$${annualGross}%.2f): ")
  println(f"Gross: $$${gross}%.2f, Net: $$${net}%.2f")

object dsl:

  class PayrollParser extends JavaTokenParsers:                 // <2>

    /** @return Parser[(Deductions)] */
    def biweekly = "biweekly" ~> "{" ~> deductions <~ "}" ^^ { ds =>
      Deductions("Biweekly", 26.0, ds)                          // <3>
    }

    /** @return Parser[Vector[Deduction]] */
    def deductions = repsep(deduction, ",") ^^ { ds =>          // <4>
      ds.toVector
    }

    /** @return Parser[Deduction] */
    def deduction =                                             // <5>
      federal_tax | state_tax | insurance | retirement


    /** @return Parser[Deduction] */
    def federal_tax = parseDeduction("federal", "tax")          // <6>
    def state_tax   = parseDeduction("state", "tax")
    def insurance   = parseDeduction("insurance", "premiums")
    def retirement  = parseDeduction("retirement", "savings")

    private def parseDeduction(word1: String, word2: String) =
      word1 ~> word2 ~> amount ^^ {
        amount => Deduction(s"${word1} ${word2}", amount)
      }

    /** @return Parser[Amount] */
    def amount = dollars | percentage                           // <7>

    /** @return Parser[Dollars] */
    def dollars = doubleNumber <~ "dollars" ^^ { d => Dollars(d) }

    /** @return Parser[Percentage] */
    def percentage = doubleNumber <~ "percent" ^^ { d => Percentage(d) }

    def doubleNumber = floatingPointNumber ^^ (_.toDouble)
