// src/main/scala/progscala3/javainterop/ComplexBean.scala
package progscala3.javainterop
import scala.annotation.targetName

/**
 * Use of the BeanProperty annotation when JavaBeans getter and setter
 * methods are required. This file will compile with Scala 2 if you remove
 * the targetName logic, which I've provided here:
 *   src/main/scala-2/progscala3/javainterop/ComplexBean2.scala
 */
case class ComplexBean(
  @scala.beans.BeanProperty var real: Double,
  @scala.beans.BeanProperty var imaginary: Double) {

  @targetName("plus") def +(that: ComplexBean) =
    ComplexBean(real + that.real, imaginary + that.imaginary)
  @targetName("minus") def -(that: ComplexBean) =
    ComplexBean(real - that.real, imaginary - that.imaginary)
}
