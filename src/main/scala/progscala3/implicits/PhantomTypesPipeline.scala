// src/main/scala/progscala3/implicits/PhantomTypesPipeline.scala
// A nicer way of driving the payroll workflow. Adapted from
// http://james-iry.blogspot.ch/2010/10/phantom-types-in-haskell-and-scala.html
// which was inspired by F# and Haskell.
package progscala3.implicits.payroll
import scala.annotation.alpha

object Pipeline:
  implicit class toPiped[V](value: V):
    @alpha("pipe") def |>[R] (f : V => R) = f(value)

@main def TryPhantomTypesPipeline =
  import Pipeline._
  import Payroll._

  val e = Employee("Buck Trends", 100000.0F, 0.25F, 200F, 0.10F, 0.05F)
  val pay = start(e) |>
    minus401k        |>
    minusInsurance   |>
    minusTax         |>
    minusFinalDeductions
  val twoWeekGross = e.annualSalary / 26.0F
  val twoWeekNet   = pay.netPay
  val percent      = (twoWeekNet / twoWeekGross) * 100
  println(s"For ${e.name}, the gross vs. net pay every 2 weeks is:")
  println(
    f"  $$${twoWeekGross}%.2f vs. $$${twoWeekNet}%.2f or ${percent}%.1f%%")
