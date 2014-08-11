// src/test/scala/progscala2/toolslibs/ComplexSpecs2.scala
// Use Specs2 instead of ScalaTest.
package progscala2.toolslibs
import org.specs2.mutable._ 

object ComplexSpec2 extends Specification { 
  "Complex addition with (0.0, 0.0)" should { 
    "return a number N' that is identical to original number N" in {
      val c1 = Complex(1.2, 3.4)
      (c1 + Complex(0.0, 0.0)) mustEqual c1
    }
  }
  "Complex subtraction with (0.0, 0.0)" should { 
    "return a number N' that is identical to original number N" in {
      val c1 = Complex(1.2, 3.4)
      (c1 - Complex(0.0, 0.0)) mustEqual c1
    }
  }
  "Complex addition" should { 
    """return a new number where 
    the real and imaginary parts are the sums of the 
    input values' real and imaginary parts, respectively.""" in {
      val c1 = Complex(1.2, 3.4)
      val c2 = Complex(5.6, 7.8)
      (c1 + c2).real mustEqual (c1.real + c2.real)
      (c1 + c2).imaginary mustEqual (c1.imaginary + c2.imaginary)
    }
  }
  "Complex subtraction" should { 
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
