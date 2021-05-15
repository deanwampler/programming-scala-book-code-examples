// tag::definitions[]
// src/script/scala/progscala3/basicoop/DollarsPercentagesOpaque.scala

object Accounting:
  opaque type Dollars = Double                             // <1>
  opaque type Percentage = Double

  object Dollars:                                          // <2>
    def apply(amount: Double): Dollars = amount

    extension (d: Dollars)
      def +(d2: Dollars): Dollars = d + d2
      def -(d2: Dollars): Dollars = d - d2
      def *(p: Percentage): Dollars = d*p
      def toDouble: Double = d
      // override def toString = f"$$$d%.2f"               // <3>
      // override def equals(other: AnyRef): Boolean = ???

  object Percentage:                                       // <4>
    def apply(amount: Double): Percentage = amount

    extension (p: Percentage)
      def +(p2: Percentage): Percentage = p + p2
      def -(p2: Percentage): Percentage = p - p2
      def *(d: Dollars): Dollars = d*p
      def toDouble: Double = p
      // override def toString = f"${(p*100.0)}%.2f%%"
      // override def equals(other: AnyRef): Boolean = ???

import Accounting.*
case class Salary(gross: Dollars, taxes: Percentage):      // <5>
  def net: Dollars = gross - (gross * taxes)
// end::definitions[]

// tag::usage[]
val gross = Dollars(10000.0)
val taxes = Percentage(0.1)
val salary1 = Salary(gross, taxes)
val net1 = salary1.net
val salary2 = Salary(taxes, gross)   // Won't compile!
// end::usage[]
