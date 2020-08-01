// src/main/scala/progscala3/basicoop/Complex.scala
package progscala3.basicoop

import scala.annotation.alpha

case class Complex(real: Double, imag: Double):
  @alpha("negate") def unary_- : Complex =
    Complex(-real, imag)
  @alpha("minus")  def -(other: Complex) =
    Complex(real - other.real, imag - other.imag)

object Complex:
  def main(args: Array[String]): Unit =
    val c1 = Complex(1.1, 2.2)
    assert(c1.real == 1.1)
    assert(c1.imag == 2.2)
    assert(-c1 == Complex(-1.1, 2.2))
    assert(c1.unary_-              == Complex(-1.1, 2.2))
    assert(c1 - Complex(0.1, 0.2)  == Complex(1.0, 2.0))
