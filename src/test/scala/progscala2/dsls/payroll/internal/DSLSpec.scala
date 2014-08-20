// src/main/scala/progscala2/dsls/payroll/internal/DSLSpec.scala
package progscala2.dsls.payroll.internal
import progscala2.dsls.payroll.common._
import scala.language.implicitConversions
import scala.language.postfixOps
import org.scalatest.{FunSpec, ShouldMatchers}
import org.scalatest.prop.Checkers._
import org.scalacheck._

// TODO: Really this should be a ScalaCheck properties test.
class DSLSpec extends FunSpec with ShouldMatchers {
  import dsl._

  val biweeklyDeductions = biweekly { deduct =>
    deduct federal_tax          (25.0  percent)
    deduct state_tax            (5.0   percent)
    deduct insurance_premiums   (500.0 dollars)
    deduct retirement_savings   (10.0  percent)
  }

  def within(d1: Double, d2: Double): Boolean = math.abs(d1 - d2) < 0.0001

  describe ("Payroll calculation") {
    it ("calculate the gross, net, and deductions for the pay period") {
      val annualGross = Gen.choose(30000.0, 200000.0)

      check(Prop.forAll(annualGross){ g =>
        val gross = biweeklyDeductions.gross(g)
        val net   = biweeklyDeductions.net(g)
        within(gross, g/26.0) &&
          within(net, (gross * (1.0 - 0.25 - 0.05 - 0.1) - 500))
      })
    }
  }
}
