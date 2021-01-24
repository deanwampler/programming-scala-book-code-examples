// src/main/scala/progscala3/toolslibs/Complex.scala
package progscala3.toolslibs
import scala.annotation.targetName

case class Complex(real: Double, imaginary: Double) derives CanEqual:

  @targetName("plus") def +(that: Complex) =
    Complex(real + that.real, imaginary + that.imaginary)
  @targetName("minus") def -(that: Complex) =
    Complex(real - that.real, imaginary - that.imaginary)
