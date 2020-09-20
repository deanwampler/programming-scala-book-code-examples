// tag::definitions[]
// src/main/scala/progscala3/contexts/NewImplicitConversions.scala
package progscala3.contexts
import scala.language.implicitConversions

case class Dollars(amount: Double):
  override def toString = f"$$$amount%.2f"

case class Percentage(amount: Double):
  override def toString = f"${(amount*100.0)}%.2f%%"

case class Salary(gross: Dollars, taxes: Percentage):
  def net: Dollars = Dollars(gross.amount * (1.0 - taxes.amount))
// end::definitions[]

// tag::entrypoint[]
@main def TryImplicitConversions() =
  given Conversion[Double,Dollars] = d => Dollars(d)            // <1>
  given Conversion[Double,Percentage] = d => Percentage(d)

  val salary = Salary(100_000.0, 0.20)
  println(s"salary: $salary. Net pay: ${salary.net}")
// end::entrypoint[]
