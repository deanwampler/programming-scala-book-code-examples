// src/test/scala/progscala3/dsls/payroll/parsercomb/DSLPropertiesSuite.scala
package progscala3.dsls.payroll.parsercomb

import progscala3.contexts.accounting.*
import munit.ScalaCheckSuite
import org.scalacheck.*

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
  val parser = PayrollParser()
  val biweeklyDeductions = parser.parseAll(parser.biweekly, input).get

  def within(d1: Dollars, d2: Dollars): Boolean =
    math.abs((d1 - d2).amount) < 0.0001

  val annualGross = Gen.choose(30000.0, 200000.0)

  property("Payroll calculator computes the pay check data") {
    forAll(annualGross) { g =>
      val dg = Dollars(g)
      val gross = biweeklyDeductions.gross(dg)
      val net   = biweeklyDeductions.net(dg)
      within(gross, dg / 26.0) &&
        within(net, (gross * Percentage(100.0 - 20.0 - 3.0 - 15.0) - Dollars(250)))
    }
  }
