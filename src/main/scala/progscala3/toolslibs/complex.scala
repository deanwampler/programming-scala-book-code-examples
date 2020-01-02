// src/main/scala/progscala3/toolslibs/Complex.scala
package progscala3.toolslibs

case class Complex(real: Double, imaginary: Double) {
  def +(that: Complex) =
    new Complex(real + that.real, imaginary + that.imaginary)
  def -(that: Complex) =
    new Complex(real - that.real, imaginary - that.imaginary)
}
