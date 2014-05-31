// src/main/scala/ToolsLibs/complex-javabean.sc

// Scala v2.11. For Scala 2.10 and earlier, use scala.reflect.BeanProperty.
case class ComplexBean(
  @scala.bean.BeanProperty real: Double, 
  @scala.bean.BeanProperty imaginary: Double) {

  def +(that: ComplexBean) = 
    new ComplexBean(real + that.real, imaginary + that.imaginary)
  def -(that: ComplexBean) = 
    new ComplexBean(real - that.real, imaginary - that.imaginary)
}
