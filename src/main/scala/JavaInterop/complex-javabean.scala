// src/main/scala/JavaInterop/complex-javabean.scala

package javainterop

// Scala v2.11. For Scala 2.10 and earlier, use scala.reflect.BeanProperty.
case class ComplexBean(
  @scala.beans.BeanProperty real: Double, 
  @scala.beans.BeanProperty imaginary: Double) {

  def +(that: ComplexBean) = 
    new ComplexBean(real + that.real, imaginary + that.imaginary)
  def -(that: ComplexBean) = 
    new ComplexBean(real - that.real, imaginary - that.imaginary)
}
