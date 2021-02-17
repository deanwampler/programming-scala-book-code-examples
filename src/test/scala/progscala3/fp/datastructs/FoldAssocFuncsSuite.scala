// src/test/scala/progscala3/fp/datastructs/FoldAssocFuncsSuite.scala
package progscala3.fp.datastructs

import munit.*

/** To fit the text, "A" == associative, "C" == commutative. .*/
class FoldAssocFuncsSuite extends FunSuite:

  // fac: a func. that is associative AND commutative.
  // Define left and right versions, because reduceLeft takes the
  // accumulator as the first parameter, while reduceRight takes it
  // as the second parameter.
  val facLeft  = (accum: Int, x: Int) => accum + x
  val facRight = (x: Int, accum: Int) => accum + x

  // fnc: a func. that is associative but NOT commutative.
  val fncLeft  = (accum: Int, x: Int) => accum - x
  val fncRight = (x: Int, accum: Int) => accum - x

  // fnac: a func. that is neither associative nor commutative.
  val fnacLeft  = (x: String, y: String) => s"($x)-($y)"
  val fnacRight = (x: String, y: String) => s"($y)-($x)"

  val list = List(1,2,3,4,5)
  val listStrings = list map (_.toString)

  test("Reducing left and right is equivalent for AC functions") {
    assert(list.reduceLeft(facLeft)   == 15)
    assert(list.reduceRight(facRight) == 15)
  }

  test("Reducing left and right are different for A, but non-C functions") {
    assert(list.reduceLeft(fncLeft)   == -13)
    assert(list.reduceRight(fncRight) ==  -5)
  }

  test("ReduceLeft is equivalent to left-biased grouping") {
    assert( ((((1 - 2) - 3) - 4) - 5) == -13)
  }

  test("ReduceRight is equivalent to right-biased grouping") {
    assert( ((((5 - 4) - 3) - 2) - 1) ==  -5)
    // or put another way, with the numbers in their original order:
    assert( (-1 + (-2 + (-3 + (-4 + 5)))) == -5)
  }

  test("x - y is associative if we note that x - y == x + -y") {
    assert( ((((1 - 2) - 3) - 4) - 5)     == -13)
    assert( ((((1 + -2) + -3) + -4) + -5) == -13)
    assert( (1 + (-2 + (-3 + (-4 + -5)))) == -13)
  }

  test("The string representation shows the grouping") {
    assert(listStrings.reduceLeft(fnacLeft)   == "((((1)-(2))-(3))-(4))-(5)")
    assert(listStrings.reduceRight(fnacRight) == "((((5)-(4))-(3))-(2))-(1)")
    assert(listStrings.reduceRight(fnacLeft)  == "(1)-((2)-((3)-((4)-(5))))")
  }
end FoldAssocFuncsSuite
