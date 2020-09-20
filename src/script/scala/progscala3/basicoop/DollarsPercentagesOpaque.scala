// tag::definitions[]
// src/script/scala/progscala3/basicoop/DollarsPercentagesOpaque.scala

object Accounting:
  opaque type Dollars = Double                             // <1>
  opaque type Percentage = Double

  object Dollars:                                          // <2>
    def apply(amount: Double): Dollars = amount

    extension (d: Dollars):
      def +(d2: Dollars): Dollars = d + d2
      def -(d2: Dollars): Dollars = d - d2
      def *(p: Percentage): Dollars = d*p
      def toDouble: Double = d
      def toString = f"$$$d%.2f"

  object Percentage:                                       // <3>
    def apply(amount: Double): Percentage = amount

    extension (p: Percentage)
      def +(p2: Percentage): Percentage = p + p2
      def -(p2: Percentage): Percentage = p - p2
      def *(d: Dollars): Dollars = d*p
      def toDouble: Double = p
      def toString = f"${(p*100.0)}%.2f%%"

import Accounting._
case class Salary(gross: Dollars, taxes: Percentage):    // <4>
  def net: Dollars = gross - (gross * taxes)
// end::definitions[]

// tag::usage[]
val gross = Dollars(10000.0)
val taxes = Percentage(0.1)
val salary1 = Salary(gross, taxes)
val net1 = salary1.net
val salary2 = Salary(taxes, gross)   // Won't compile!
val net2 = salary2.net
// end::usage[]
