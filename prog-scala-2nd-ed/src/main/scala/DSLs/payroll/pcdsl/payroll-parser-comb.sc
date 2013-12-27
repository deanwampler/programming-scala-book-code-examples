// src/main/scala/DSLs/payroll/pcdsl/payroll-parser-comb.sc

import scala.util.parsing.combinator._
import dsls.payroll.pcdsl._

val p = new PayrollParserCombinatorsV1
p.empl
p.weeksDays
p.doubleNumber
p.deduct
p.paycheck
p.parseAll(p.weeksDays, "weeks")
val input = """paycheck for employee "Buck Trends"
is salary for 2 weeks minus deductions for {}"""
p.parseAll(p.paycheck, input)
