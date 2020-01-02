// src/main/scala/progscala3/basicoop/Complex.sc

case class Complex(real: Double, imag: Double) {
  def unary_- : Complex = Complex(-real, imag)                       // <1>
  def -(other: Complex) = Complex(real - other.real, imag - other.imag)
}

val c1 = Complex(1.1, 2.2)
assert(c1.real == 1.1)
assert(c1.imag == 2.2)
assert(-c1 == Complex(-1.1, 2.2))
assert(c1.unary_-              == Complex(-1.1, 2.2))
assert(c1 - Complex(0.1, 0.2)  == Complex(1.0, 2.0))
