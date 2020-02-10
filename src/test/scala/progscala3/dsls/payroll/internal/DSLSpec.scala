// src/main/scala/progscala3/dsls/payroll/internal/DSLSpec.scala
package progscala3.dsls.payroll.internal
import scala.language.implicitConversions
import org.scalacheck._
import progscala3.dsls.payroll.common._
import scala.language.postfixOps

// TODO: Really this should be a "full" ScalaCheck properties test.
class DSLSpec extends Properties("Payroll DSL2") {
  import dsl._
  import Prop.forAll

  val biweeklyDeductions = biweekly { deduct =>
    deduct.federal_tax       (25.0  percent)
    deduct.state_tax         (5.0   percent)
    deduct.insurance_premiums(500.0 dollars)
    deduct.retirement_savings(10.0  percent)
  }

  def within(d1: Double, d2: Double): Boolean = math.abs(d1 - d2) < 0.0001

  property("Payroll calculator computes the pay check data") =
    forAll(annualGross) { g =>
      val gross = biweeklyDeductions.gross(g)
      val net   = biweeklyDeductions.net(g)
      within(gross, g/26.0) &&
        within(net, (gross * (1.0 - 0.25 - 0.05 - 0.1) - 500))
      }
}
