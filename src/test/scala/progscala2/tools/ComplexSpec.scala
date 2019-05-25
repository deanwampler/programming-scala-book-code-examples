// src/test/scala/progscala2/toolslibs/ComplexSpec.scala
package progscala2.toolslibs
import org.scalatest.{ FunSpec, Matchers }

class ComplexSpec extends FunSpec with Matchers {
  describe ("Complex addition with (0.0, 0.0)") {
    it ("returns a number N' that is identical to original number N") {
      val c1 = Complex(1.2, 3.4)
      (c1 + Complex(0.0, 0.0)) shouldEqual c1
    }
  }
  describe ("Complex subtraction with (0.0, 0.0)") {
    it ("returns a number N' that is identical to original number N") {
      val c1 = Complex(1.2, 3.4)
      (c1 - Complex(0.0, 0.0)) shouldEqual c1
    }
  }
  describe ("Complex addition") {
    it ("returns a new number where the real and imaginary parts are the sums of the input values' real and imaginary parts, respectively.") {
      val c1 = Complex(1.2, 3.4)
      val c2 = Complex(5.6, 7.8)
      (c1 + c2).real shouldEqual (c1.real + c2.real)
      (c1 + c2).imaginary shouldEqual (c1.imaginary + c2.imaginary)
    }
  }
  describe ("Complex subtraction") {
    it ("return a new number where the real and imaginary parts are the differences of the input values' real and imaginary parts, respectively.") {
      val c1 = Complex(1.2, 3.4)
      val c2 = Complex(5.6, 7.8)
      (c1 - c2).real shouldEqual (c1.real - c2.real)
      (c1 - c2).imaginary shouldEqual (c1.imaginary - c2.imaginary)
    }
  }
}
