// src/main/scala/progscala2/appdesign/parthenon/PayrollUseCases.scala
package progscala2.appdesign.parthenon
import progscala2.dsls.payroll.parsercomb.dsl.PayrollParser
import progscala2.dsls.payroll.common._

object PayrollParthenon {                                            // <1>
  val dsl = """biweekly {
      federal tax          %f  percent,
      state tax            %f  percent,
      insurance premiums   %f  dollars,
      retirement savings   %f  percent
    }"""
                                                                     // <2>
  private def readData(inputFileName: String): Seq[(String, Money, String)] =
    for {
      line <- scala.io.Source.fromFile(inputFileName).getLines.toVector
      if line.matches("\\s*#.*") == false  // skip comments
    } yield toRule(line)

  private def toRule(line: String): (String, Money, String) = {      // <3>
    val Array(name, salary, fedTax, stateTax, insurance, retirement) =
      line.split("""\s*,\s*""")
    val ruleString = dsl.format(
      fedTax.toDouble, stateTax.toDouble,
      insurance.toDouble, retirement.toDouble)
    (name, Money(salary.toDouble), ruleString)
  }

  private val parser = new PayrollParser                             // <4>

  private def toDeduction(rule: String) =
    parser.parseAll(parser.biweekly, rule).get

  private type EmployeeData = (String, Money, Deductions)            // <5>
                                                                     // <6>
  private def processRules(inputFileName: String): Seq[EmployeeData] = {
    val data = readData(inputFileName)
    for {
      (name, salary, rule) <- data
    } yield (name, salary, toDeduction(rule))
  }
                                                                     // <7>
  def biweeklyPayrollPerEmployeeReportUseCase(data: Seq[EmployeeData]): Unit ={
    val fmt  = "%-10s %6.2f  %5.2f  %5.2f\n"
    val head = "%-10s %-7s  %-5s    %s\n"
    println("\nBiweekly Payroll:")
    printf(head, "Name", "Gross", "Net", "Deductions")
    printf(head, "----", "-----", "---", "----------")
    for {
      (name, salary, deductions) <- data
      gross = deductions.gross(salary.amount)
      net   = deductions.net(salary.amount)
    } printf(fmt, name, gross, net, gross - net)
  }
                                                                     // <8>
  def biweeklyPayrollTotalsReportUseCase(data: Seq[EmployeeData]): Unit = {
    val (gross, net) = (data foldLeft (0.0, 0.0)) {
      case ((gross, net), (name, salary, deductions)) =>
      val g = deductions.gross(salary.amount)
      val n = deductions.net(salary.amount)
      (gross + g, net + n)
    }
    printf("\nBiweekly Totals: Gross %7.2f, Net %6.2f, Deductions: %6.2f\n",
      gross, net, gross - net)
  }

  def main(args: Array[String]) = {
    val inputFileName =
      if (args.length > 0) args(0) else "misc/parthenon-payroll.txt"
    val data = processRules(inputFileName)

    biweeklyPayrollTotalsReportUseCase(data)
    biweeklyPayrollPerEmployeeReportUseCase(data)
  }
}
