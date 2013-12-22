// code-examples/ToolsLibs/complex-spec.scala

import org.scalatest.{ FunSpec, ShouldMatchers } 

class ComplexSpec extends FunSpec with ShouldMatchers { 
  describe ("Complex addition with (0.0, 0.0)") { 
    it ("return a number N' that is identical to original number N") {
      val c1 = Complex(1.2, 3.4)
      (c1 + Complex(0.0, 0.0)) mustEqual c1
    }
  }
  describe ("Complex subtraction with (0.0, 0.0)") { 
    it ("return a number N' that is identical to original number N") {
      val c1 = Complex(1.2, 3.4)
      (c1 - Complex(0.0, 0.0)) mustEqual c1
    }
  }
  describe ("Complex addition") { 
    """return a new number where 
    the real and imaginary parts are the sums of the 
    input values' real and imaginary parts, respectively.""" in {
      val c1 = Complex(1.2, 3.4)
      val c2 = Complex(5.6, 7.8)
      (c1 + c2).real mustEqual (c1.real + c2.real)
      (c1 + c2).imaginary mustEqual (c1.imaginary + c2.imaginary)
    }
  }
  describe ("Complex subtraction") { 
    """return a new number where 
    the real and imaginary parts are the differences of the 
    input values' real and imaginary parts, respectively.""" in {
      val c1 = Complex(1.2, 3.4)
      val c2 = Complex(5.6, 7.8)
      (c1 - c2).real mustEqual (c1.real - c2.real)
      (c1 - c2).imaginary mustEqual (c1.imaginary - c2.imaginary)
    }
  }
}
