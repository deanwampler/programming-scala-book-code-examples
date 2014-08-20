// src/main/scala/progscala2/dsls/payroll/parsercomb/DSLSpec.scala
package progscala2.dsls.payroll.parsercomb
import progscala2.dsls.payroll.common._
import scala.language.implicitConversions
import scala.language.postfixOps
import org.scalatest.{FunSpec, ShouldMatchers}
import org.scalatest.prop.Checkers._
import org.scalacheck._

// TODO: Really this should be a ScalaCheck properties test.
class DSLSpec extends FunSpec with ShouldMatchers {
  import dsl.PayrollParser

  val input = """biweekly {
    federal tax          20.0  percent,
    state tax            3.0   percent,
    insurance premiums   250.0 dollars,
    retirement savings   15.0  percent
  }"""
  val parser = new PayrollParser
  val biweeklyDeductions = parser.parseAll(parser.biweekly, input).get

  def within(d1: Double, d2: Double): Boolean = math.abs(d1 - d2) < 0.0001

  describe ("Payroll calculation") {
    it ("calculate the gross, net, and deductions for the pay period") {
      val annualGross = Gen.choose(30000.0, 200000.0)
      check(Prop.forAll(annualGross){ g =>
        val gross = biweeklyDeductions.gross(g)
        val net   = biweeklyDeductions.net(g)
        within(gross, g/26.0) &&
          within(net, (gross * (1.0 - 0.20 - 0.03 - 0.15) - 250))
      })
    }
  }
}
