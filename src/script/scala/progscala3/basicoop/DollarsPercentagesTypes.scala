// tag::definitions[]
// src/script/scala/progscala3/basicoop/DollarsPercentagesTypes.scala

object Accounting:
  type Dollars = Double
  type Percentage = Double

import Accounting._
case class Salary(gross: Dollars, taxes: Percentage):
  def net: Dollars = gross * (1.0 - taxes)
  override def toString =
    f"Salary(gross = $$$gross%.2f, taxes = ${(taxes*100.0)}%.2f%%)"
// end::definitions[]

// tag::oops[]
import Accounting._
val gross: Dollars = 10000.0
val taxes: Percentage = 0.1
val salary1 = Salary(gross, taxes)
val net1 = salary1.net
val salary2 = Salary(taxes, gross)   // Error!!
val net2 = salary2.net
// end::oops[]
