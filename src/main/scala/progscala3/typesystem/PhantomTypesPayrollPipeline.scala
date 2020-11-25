// src/main/scala/progscala3/typesystem/PhantomTypesPayrollPipeline.scala
package progscala3.typesystem.payroll
import scala.annotation.targetName

object Pipeline:
  implicit class toPiped[V](value: V):
    @targetName("pipe") def |>[R] (f : V => R) = f(value)

@main def TryPhantomTypesPipeline =
  import Pipeline._
  import Payroll._

  val e = Employee("Buck Trends", 100000.0, 0.25, 200, 0.10, 100.0)
  val pay = start(e) |>
    minus401k        |>
    minusInsurance   |>
    minusTax         |>
    minusFinalDeductions
  println(e); println(pay)
