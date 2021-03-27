// src/test/scala/progscala3/dsls/payroll/internal/DSLPropertiesSuite.scala
package progscala3.dsls.payroll.internal

import munit.ScalaCheckSuite
import org.scalacheck.*
import progscala3.dsls.payroll.*
import progscala3.contexts.accounting.*

/**
 * ScalaCheck example driven by MUnit
 * TODO: Really this should be a "full" ScalaCheck properties test.
 */
class DSLPropertiesSuite extends ScalaCheckSuite:
  import dsl.*
  import Prop.forAll

  val biweeklyDeductions = biweekly { deduct =>
    deduct.federal_tax       (25.0.percent)
    deduct.state_tax         (5.0.percent)
    deduct.insurance_premiums(500.0.dollars)
    deduct.retirement_savings(10.0.percent)
  }

  def within(d1: Dollars, d2: Dollars): Boolean =
    math.abs((d1 - d2).amount) < 0.0001

  val annualGross = Gen.choose(30000.0, 200000.0)

  property("Payroll calculator computes the pay check data") {
    forAll(annualGross) { g =>
      val dg = Dollars(g)
      val gross = biweeklyDeductions.gross(dg)
      val net   = biweeklyDeductions.net(dg)
      within(gross, dg / 26.0) &&
        within(net, (gross * Percentage(100.0 - 25.0 - 5.0 - 10.0) - Dollars(500)))
      }
  }
