// src/main/scala/progscala2/dsls/payroll/parsercomb/dsl.scala
package progscala2.dsls.payroll.parsercomb
import scala.util.parsing.combinator._
import progscala2.dsls.payroll.common._                              // <1>

object Payroll {
  
  import dsl.PayrollParser                                           // <2>

  def main(args: Array[String]) = {                                  // <3> 
    val input = """biweekly {      
      federal tax          20.0  percent,
      state tax            3.0   percent,
      insurance premiums   250.0 dollars,
      retirement savings   15.0  percent
    }"""
    val parser = new PayrollParser                                   // <4> 
    val biweeklyDeductions = parser.parseAll(parser.biweekly, input).get

    println(biweeklyDeductions)                                      // <5>
    val annualGross = 100000.0
    val gross = biweeklyDeductions.gross(annualGross)
    val net   = biweeklyDeductions.net(annualGross)
    print(f"Biweekly pay (annual: $$${annualGross}%.2f): ")
    println(f"Gross: $$${gross}%.2f, Net: $$${net}%.2f")
  }
}

object dsl {                                    

  class PayrollParser extends JavaTokenParsers {                     // <1>

    /** @return Parser[(Deductions)] */
    def biweekly = "biweekly" ~> "{" ~> deductions <~ "}" ^^ { ds => // <2> 
      Deductions("Biweekly", 26.0, ds)
    }

    /** @return Parser[Vector[Deduction]] */
    def deductions = repsep(deduction, ",") ^^ { ds =>               // <3>
      ds.foldLeft(Vector.empty[Deduction]) (_ :+ _)
    }

    /** @return Parser[Deduction] */
    def deduction = federal_tax | state_tax | insurance | retirement // <4>

    /** @return Parser[Deduction] */
    def federal_tax = parseDeduction("federal", "tax")               // <5>
    def state_tax   = parseDeduction("state", "tax")
    def insurance   = parseDeduction("insurance", "premiums")
    def retirement  = parseDeduction("retirement", "savings")

    private def parseDeduction(word1: String, word2: String) =       // <6> 
      word1 ~> word2 ~> amount ^^ { 
        amount => Deduction(s"${word1} ${word2}", amount)
      }

    /** @return Parser[Amount] */
    def amount = dollars | percentage                                // <7>

    /** @return Parser[Dollars] */
    def dollars = doubleNumber <~ "dollars" ^^ { d => Dollars(d) }

    /** @return Parser[Percentage] */
    def percentage = doubleNumber <~ "percent" ^^ { d => Percentage(d) }

    def doubleNumber = floatingPointNumber ^^ (_.toDouble)
  }
}
