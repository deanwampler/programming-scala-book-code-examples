// src/main/scala/progscala2/dsls/payroll/internal/dsl.scala
package progscala2.dsls.payroll.internal
import scala.language.postfixOps                                     // <1>
import progscala2.dsls.payroll.common._

object Payroll {                                                     // <2>
  
  import dsl._                                                       // <3>

  def main(args: Array[String]) = {
    val biweeklyDeductions = biweekly { deduct =>                    // <4>
      deduct federal_tax          (25.0  percent)
      deduct state_tax            (5.0   percent)
      deduct insurance_premiums   (500.0 dollars)
      deduct retirement_savings   (10.0  percent)
    }

    println(biweeklyDeductions)                                      // <5>
    val annualGross = 100000.0
    val gross = biweeklyDeductions.gross(annualGross)
    val net   = biweeklyDeductions.net(annualGross)
    print(f"Biweekly pay (annual: $$${annualGross}%.2f): ")
    println(f"Gross: $$${gross}%.2f, Net: $$${net}%.2f")
  }
}

object dsl {                                                         // <1>

  def biweekly(f: DeductionsBuilder => Deductions) =                 // <2>
    f(new DeductionsBuilder("Biweekly", 26.0))

  class DeductionsBuilder(                                           // <3>
    name: String,
    divisor: Double = 1.0,
    deducts: Vector[Deduction] = Vector.empty) extends Deductions(
      name, divisor, deducts) {

    def federal_tax(amount: Amount): DeductionsBuilder = {           // <4>
      deductions = deductions :+ Deduction("federal taxes", amount)
      this
    }

    def state_tax(amount: Amount): DeductionsBuilder = {
      deductions = deductions :+ Deduction("state taxes", amount)
      this
    }

    def insurance_premiums(amount: Amount): DeductionsBuilder = {
      deductions = deductions :+ Deduction("insurance premiums", amount)
      this
    }

    def retirement_savings(amount: Amount): DeductionsBuilder = {
      deductions = deductions :+ Deduction("retirement savings", amount)
      this
    }
  }
}

