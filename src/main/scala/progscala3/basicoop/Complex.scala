// tag::definitions[]
// src/main/scala/progscala3/basicoop/Complex.scala
package progscala3.basicoop

import scala.annotation.targetName

case class Complex(real: Double, imag: Double):
  @targetName("negate") def unary_- : Complex =                 // <1>
    Complex(-real, imag)
  @targetName("minus")  def -(other: Complex) =                 // <2>
    Complex(real - other.real, imag - other.imag)
// end::definitions[]

// tag::entrypoint[]
@main def TryComplex: Unit =
  val c1 = Complex(1.1, 2.2)
  assert(c1.real == 1.1)
  assert(c1.imag == 2.2)
  assert(-c1 == Complex(-1.1, 2.2))                             // <3>
  assert(c1.unary_-              == Complex(-1.1, 2.2))
  assert(c1 - Complex(0.1, 0.2)  == Complex(1.0, 2.0))
// end::entrypoint[]
