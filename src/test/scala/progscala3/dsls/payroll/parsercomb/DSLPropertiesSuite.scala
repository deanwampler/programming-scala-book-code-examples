// src/test/scala/progscala3/dsls/payroll/parsercomb/DSLPropertiesSuite.scala
package progscala3.dsls.payroll.parsercomb

import munit.ScalaCheckSuite
import org.scalacheck._

/**
 * ScalaCheck example driven by MUnit
 * TODO: Really this should be a "full" ScalaCheck properties test.
 */
class DSLPropertiesSuite extends ScalaCheckSuite:
  import dsl.PayrollParser
  import Prop.forAll

  val input = """biweekly {
    federal tax          20.0  percent,
    state tax            3.0   percent,
    insurance premiums   250.0 dollars,
    retirement savings   15.0  percent
  }"""
  val parser = new PayrollParser
  val biweeklyDeductions = parser.parseAll(parser.biweekly, input).get

  def within(d1: Double, d2: Double): Boolean = math.abs(d1 - d2) < 0.0001

  val annualGross = Gen.choose(30000.0, 200000.0)

  property("Payroll calculator computes the pay check data") {
    forAll(annualGross) { g =>
      val gross = biweeklyDeductions.gross(g)
      val net   = biweeklyDeductions.net(g)
      within(gross, g/26.0) &&
        within(net, (gross * (1.0 - 0.20 - 0.03 - 0.15) - 250))
    }
  }