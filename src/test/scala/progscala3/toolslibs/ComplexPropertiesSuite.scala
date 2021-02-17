// src/test/scala/progscala3/toolslibs/ComplexPropertiesSuite.scala
package progscala3.toolslibs

import munit.ScalaCheckSuite
import org.scalacheck.*

class ComplexPropertiesSuite extends ScalaCheckSuite:
  import Prop.forAll

  def additionTest(a: Complex, b: Complex) =
    (a + b).real == (a.real + b.real) &&
    (a + b).imaginary == (a.imaginary + b.imaginary)

  def subtractionTest(a: Complex, b: Complex) =
    (a - b).real == (a.real - b.real) &&
    (a - b).imaginary == (a.imaginary - b.imaginary)

  val zero = Complex(0.0, 0.0)

  property("Complex addition with the identity element (zero)") {
    forAll { (real: Double, imag: Double) =>
      val c = Complex(real, imag)
      additionTest(zero, c) &&
      additionTest(c, zero)
    }
  }

  property("Complex subtraction with the identity element (zero)") {
    forAll { (real: Double, imag: Double) =>
      val c = Complex(real, imag)
      subtractionTest(zero, c) &&
      subtractionTest(c, zero)
    }
  }

  property("Complex addition with two values") {
    forAll { (real1: Double, imag1: Double, real2: Double, imag2: Double) =>
      val c1 = Complex(real1, imag1)
      val c2 = Complex(real2, imag2)
      additionTest(c1, c2)
    }
  }

  property("Complex subtraction with two values") {
    forAll { (real1: Double, imag1: Double, real2: Double, imag2: Double) =>
      val c1 = Complex(real1, imag1)
      val c2 = Complex(real2, imag2)
      subtractionTest(c1, c2)
    }
  }
