// src/main/scala/progscala3/toolslibs/Complex.scala
package progscala3.toolslibs
import scala.annotation.alpha

case class Complex(real: Double, imaginary: Double) {

  @alpha("plus") def +(that: Complex) =
    new Complex(real + that.real, imaginary + that.imaginary)
  @alpha("minus") def -(that: Complex) =
    new Complex(real - that.real, imaginary - that.imaginary)
}
