// src/main/scala-2/progscala3/javainterop/ComplexBean2.scala
package progscala3.javainterop

case class ComplexBean2(
  @scala.beans.BeanProperty var real: Double,
  @scala.beans.BeanProperty var imaginary: Double) {

  def +(that: ComplexBean2) =
    ComplexBean2(real + that.real, imaginary + that.imaginary)
  def -(that: ComplexBean2) =
    ComplexBean2(real - that.real, imaginary - that.imaginary)
}
