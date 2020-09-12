// src/main/scala/progscala3/appdesign/parthenon/PayrollUseCases.scala
package progscala3.appdesign.parthenon
import progscala3.dsls.payroll.parsercomb.dsl.PayrollParser

object PayrollUseCases:
  import PayrollCalculator.{fromFile, Pay}

  def biweeklyPayrollPerEmployee(data: Seq[Pay]): Unit =
    val fmt  = "%-10s %6.2f  %5.2f  %5.2f\n"
    val head = "%-10s %-7s  %-5s    %s\n"
    println("\nBiweekly Payroll:")
    printf(head, "Name", "Gross", "Net", "Deductions")
    printf(head, "----", "-----", "---", "----------")
    for
      Pay(name, salary, deductions) <- data
      gross = deductions.gross(salary.amount)
      net   = deductions.net(salary.amount)
    do printf(fmt, name, gross, net, gross - net)

  def biweeklyPayrollTotalsReport(data: Seq[Pay]): Unit =
    val (gross, net) = data.foldLeft(0.0 -> 0.0) {
      case ((gross, net), Pay(_, salary, deductions)) =>
      val g = deductions.gross(salary.amount)
      val n = deductions.net(salary.amount)
      (gross + g, net + n)
    }
    printf("\nBiweekly Totals: Gross %7.2f, Net %6.2f, Deductions: %6.2f\n",
      gross, net, gross - net)

  def main(params: Array[String]): Unit =
    val inputFileName =
      if params.length > 0 then params(0) else "misc/parthenon-payroll.txt"
    val data = fromFile(inputFileName)

    biweeklyPayrollPerEmployee(data)
    biweeklyPayrollTotalsReport(data)
