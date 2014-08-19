// src/main/scala/progscala2/toolslibs/toolslibs/ComplexProperties.scala
package progscala2.toolslibs
import org.scalatest.FunSuite
import org.scalatest.prop.PropertyChecks

class ComplexProperties extends FunSuite with PropertyChecks {

  def additionTest(a: Complex, b: Complex) = {
    assert( (a + b).real === (a.real + b.real) )
    assert( (a + b).imaginary === (a.imaginary + b.imaginary) )
  }

  def subtractionTest(a: Complex, b: Complex) = {
    assert( (a - b).real === (a.real - b.real) )
    assert( (a - b).imaginary === (a.imaginary - b.imaginary) )
  }

  val zero = Complex(0.0, 0.0)

  test ("Complex addition with the identity element (zero)") {
    forAll { (real: Double, imag: Double) =>
      val c = Complex(real, imag)
      additionTest(zero, c)
      additionTest(c, zero)
    }
  }

  test ("Complex subtraction with the identity element (zero)") {
    forAll { (real: Double, imag: Double) =>
      val c = Complex(real, imag)
      subtractionTest(zero, c)
      subtractionTest(c, zero)
    }
  }

  test ("Complex addition with two values") {
    forAll { (real1: Double, imag1: Double, real2: Double, imag2: Double) =>
      val c1 = Complex(real1, imag1)
      val c2 = Complex(real2, imag2)
      additionTest(c1, c2)
    }
  }

  test ("Complex subtraction with two values") {
    forAll { (real1: Double, imag1: Double, real2: Double, imag2: Double) =>
      val c1 = Complex(real1, imag1)
      val c2 = Complex(real2, imag2)
      subtractionTest(c1, c2)
    }
  }
}
