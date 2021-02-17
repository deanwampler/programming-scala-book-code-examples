// tag::definitions[]
// src/script/scala/progscala3/basicoop/DollarsPercentagesTypes.scala

object Accounting:
  type Dollars = Double
  type Percentage = Double

import Accounting.*
case class Salary(gross: Dollars, taxes: Percentage):
  def net: Dollars = gross * (1.0 - (taxes/100.0))
  override def toString =
    f"Salary(gross = $$$gross%.2f, taxes = $taxes%.2f%%)"
// end::definitions[]

// tag::oops[]
import Accounting.*
val gross: Dollars = 10000.0
val taxes: Percentage = 10.0
val salary1 = Salary(gross, taxes)
val net1 = salary1.net
val salary2 = Salary(taxes, gross)   // Error, but it compiles!!
val net2 = salary2.net
// end::oops[]
