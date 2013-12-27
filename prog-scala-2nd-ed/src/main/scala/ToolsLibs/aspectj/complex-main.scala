// src/main/scala/ToolsLibs/aspectj/complex-main.scala

package toolslibs.aspectj

object ComplexMain {
  def main(args: Array[String]) {
    val c1 = Complex(1.0, 2.0)
    val c2 = Complex(3.0, 4.0)
    val c12 = c1 + c2
    println(c12)
  }
}
