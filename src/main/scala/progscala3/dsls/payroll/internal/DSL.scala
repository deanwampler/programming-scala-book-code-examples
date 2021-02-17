// src/main/scala/progscala3/dsls/payroll/internal/DSL.scala
package progscala3.dsls.payroll.internal
import scala.language.postfixOps                                     // <1>
import progscala3.dsls.payroll.*
import progscala3.contexts.accounting.*

@main def TryPayroll =
  import dsl.*                                                       // <2>
  val biweeklyDeductions = biweekly { deduct =>                      // <3>
    deduct federal_tax        (25.0  percent)
    deduct state_tax          (5.0   percent)
    deduct insurance_premiums (500.0 dollars)
    deduct retirement_savings (10.0  percent)
  }

  println(biweeklyDeductions)                                        // <4>
  val annualGross = Dollars(100000.0)
  val gross = biweeklyDeductions.gross(annualGross)
  val net   = biweeklyDeductions.net(annualGross)
  print(f"Biweekly pay (annual: $annualGross): ")
  println(f"Gross: $gross, Net: $net")

object dsl:
  def biweekly(                                                      // <5>
      db: DeductionsBuilder => DeductionsBuilder): Deductions =
    db(DeductionsBuilder("Biweekly", 26)).deductions

  case class DeductionsBuilder(                                      // <6>
    name: String,
    annualPayPeriods: Int):

    private var all: Vector[Deduction] = Vector.empty

    def deductions: Deductions = Deductions(name, annualPayPeriods, all)

    infix def federal_tax(amount: Percentage): DeductionsBuilder =   // <7>
      all = all :+ PercentageDeduction("federal taxes", amount)
      this

    infix def state_tax(amount: Percentage): DeductionsBuilder =
      all = all :+ PercentageDeduction("state taxes", amount)
      this

    infix def insurance_premiums(amount: Dollars): DeductionsBuilder =
      all = all :+ DollarsDeduction("insurance premiums", amount)
      this

    infix def retirement_savings(amount: Percentage): DeductionsBuilder =
      all = all :+ PercentageDeduction("retirement savings", amount)
      this
end dsl
