// src/main/scala/progscala3/contexts/NewImplicitConversions.scala
package progscala3.contexts
import scala.language.implicitConversions                       // <1>

case class Dollars(amount: Double):                             // <2>
  override def toString = f"$$$amount%8.2f"

case class Percentage(amount: Double):                          // <3>
  assert(amount >= 0.0 && amount <= 1.0)
  override def toString = f"${(amount*100.0)}%3.2f%%"

case class Salary(gross: Dollars, taxes: Percentage):
  def net: Dollars = Dollars(gross.amount * (1.0 - taxes.amount))

@main def TryImplicitConversions() =
  given Conversion[Double,Dollars] = d => Dollars(d)            // <4>
  given Conversion[Double,Percentage] = d => Percentage(d)

  val salary = Salary(100_000.0, 0.20)
  println(s"salary: $salary. Net pay: ${salary.net}")
