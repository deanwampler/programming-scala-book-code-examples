// src/test/scala/progscala3/fp/combinators/PayrollSuite.scala
package progscala3.fp.combinators

import munit.*

class PayrollSuite extends FunSuite:

  case class Employee (name: String, title: String, annualSalary: Double,
    taxRate: Double, insurancePremiumsPerWeek: Double)

  val employees = List(
    Employee("Buck Trends", "CEO", 200000, 0.25, 100.0),
    Employee("Cindy Banks", "CFO", 170000, 0.22, 120.0),
    Employee("Joe Coder", "Developer", 130000, 0.20, 120.0))

  val weeklyPayroll = employees map { e =>
    val net = (1.0 - e.taxRate) * (e.annualSalary / 52.0) -
      e.insurancePremiumsPerWeek
    (e, net)
  }

  test("weeklyPayroll computes pay for each employee") {
    val results1 = weeklyPayroll map {
      case (e, net) => (e.name, f"${net}%.2f")
    }
    assert(results1 == List(
      ("Buck Trends", "2784.62"),
      ("Cindy Banks", "2430.00"),
      ("Joe Coder",   "1880.00")))
  }

  test("from weeklyPayroll, the totals can be calculated") {
    val report = weeklyPayroll.foldLeft( (0.0, 0.0, 0.0) ) {
      case ((totalSalary, totalNet, totalInsurance), (e, net)) =>
        (totalSalary + e.annualSalary/52.0,
          totalNet + net, totalInsurance + e.insurancePremiumsPerWeek)
    }
    assert(f"${report._1}%.2f" == "9615.38", "total salary")
    assert(f"${report._2}%.2f" == "7094.62", "total net")
    assert(f"${report._3}%.2f" == "340.00" , "total insurance")
  }
