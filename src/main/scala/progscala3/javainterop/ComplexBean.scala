// src/main/scala/progscala3/javainterop/ComplexBean.scala
package progscala3.javainterop
import scala.annotation.targetName

// Scala 2.11. For Scala 2.10 and earlier, use scala.reflect.BeanProperty.
case class ComplexBean(
  @scala.beans.BeanProperty real: Double,
  @scala.beans.BeanProperty imaginary: Double):

  @targetName("plus") def +(that: ComplexBean) =
    new ComplexBean(real + that.real, imaginary + that.imaginary)
  @targetName("minus") def -(that: ComplexBean) =
    new ComplexBean(real - that.real, imaginary - that.imaginary)
