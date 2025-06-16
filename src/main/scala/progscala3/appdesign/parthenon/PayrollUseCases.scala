// src/main/scala/progscala3/appdesign/parthenon/PayrollUseCases.scala
package progscala3.appdesign.parthenon
import progscala3.dsls.payroll.parsercomb.dsl.PayrollParser
import progscala3.contexts.accounting.*

object PayrollUseCases:
  import PayrollCalculator.{fromFile, Pay}
  val fmt  = "%-10s %8.2f  %8.2f     %5.2f\n"
  val head = "%-10s %-8s  %-8s  %s\n"

  def biweeklyPayrollPerEmployee(data: Seq[Pay]): Unit =        // <1>
    println("\nBiweekly Payroll:")
    printf(head, "Name", "   Gross", "     Net", "Deductions")
    println("-----------------------------------------")
    for
      Pay(name, salary, deductions) <- data
      gross = deductions.gross(salary)
      net   = deductions.net(salary)
    do printf(fmt, name, gross.amount, net.amount, (gross - net).amount)

  def biweeklyPayrollTotalsReport(data: Seq[Pay]): Unit =
    val (gross, net) = data.foldLeft(Dollars.zero -> Dollars.zero) {
      case ((gross, net), Pay(_, salary, deductions)) =>
      val g = deductions.gross(salary)
      val n = deductions.net(salary)
      (gross + g, net + n)
    }
    println("-----------------------------------------")
    printf(fmt, "Totals", gross.amount, net.amount, (gross - net).amount)

  @main def RunPayroll(inputFileNames: String*): Unit =
    val files =
      if inputFileNames.length == 0 then Seq("misc/parthenon-payroll.txt")
      else inputFileNames
    for file <- files do
      println(s"Processing input file: $file")
      val data = fromFile(file)
      biweeklyPayrollPerEmployee(data)
      biweeklyPayrollTotalsReport(data)
