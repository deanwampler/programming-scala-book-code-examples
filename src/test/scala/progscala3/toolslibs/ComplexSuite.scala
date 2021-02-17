// src/test/scala/progscala3/toolslibs/ComplexSuite.scala
package progscala3.toolslibs

import munit.*

class ComplexSuite extends FunSuite:

  val c1 = Complex(1.2, 3.4)
  val c2 = Complex(5.6, 7.8)

  test("addition with (0, 0)") {
    assert(c1 + Complex(0.0, 0.0) == c1)
  }

  test("subtraction with (0, 0)") {
    assert(c1 - Complex(0.0, 0.0) == c1)
  }

  test("addition") {
    assert((c1 + c2).real == (c1.real + c2.real))
    assert((c1 + c2).imaginary == (c1.imaginary + c2.imaginary))
  }

  test("subtraction") {
    assert((c1 - c2).real == (c1.real - c2.real))
    assert((c1 - c2).imaginary ==  (c1.imaginary - c2.imaginary))
  }
