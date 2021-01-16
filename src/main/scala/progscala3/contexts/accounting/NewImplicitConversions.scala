// tag::definitions[]
// src/main/scala/progscala3/contexts/accounting/NewImplicitConversions.scala
package progscala3.contexts.accounting

case class Dollars(amount: Double):
  override def toString = f"$$$amount%.2f"
  def +(d: Dollars): Dollars = Dollars(amount + d.amount)       // <1>
  def -(d: Dollars): Dollars = Dollars(amount - d.amount)
  def /(d: Double): Dollars  = Dollars(amount / d)
  def *(p: Percentage): Dollars = Dollars(amount * p.toMultiplier)

object Dollars:
  val zero = Dollars(0.0)

/**
 * @param amount where 11.0 means 11%, so 11% of 100 == 11.0.
 */
case class Percentage(amount: Double):
  override def toString = f"${amount}%.2f%%"
  def toMultiplier: Double = amount/100.0
  def +(p: Percentage): Percentage = Percentage(amount + p.amount)
  def -(p: Percentage): Percentage = Percentage(amount - p.amount)
  def *(p: Percentage): Percentage = Percentage(toMultiplier * p.toMultiplier)
  def *(d: Dollars): Dollars = d * this

object Percentage:
  val hundredPercent = Percentage(100.0)
  val zero = Percentage(0.0)

case class Salary(gross: Dollars, taxes: Percentage):
  def net: Dollars = gross * (Percentage.hundredPercent - taxes)
// end::definitions[]

// tag::entrypoint[]

@main def TryImplicitConversions() =
  import scala.language.implicitConversions                     // <1>

  given Conversion[Double,Dollars] = d => Dollars(d)            // <2>
  given Conversion[Double,Percentage] = d => Percentage(d)

  val salary = Salary(100_000.0, 20.0)
  println(s"salary: $salary. Net pay: ${salary.net}")

  given Conversion[Int,Dollars] with                            // <3>
    def apply(i:Int): Dollars= Dollars(i.toDouble)

  val dollars: Dollars = 10                                     // <4>
  println(s"Dollars created from an Int: $dollars")
// end::entrypoint[]
