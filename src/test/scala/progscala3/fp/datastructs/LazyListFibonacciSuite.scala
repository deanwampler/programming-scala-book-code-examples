// src/test/scala/progscala3/fp/datastructs/LazyListFibonacciSuite.scala
package progscala3.fp.datastructs

import munit.*
import scala.math.BigInt

class FibonacciSuite extends FunSuite:

  test("Compute Fibonacci numbers the lazy way!") {
    // Must convert to an "eager" sequence to see the values:
    assert(Fibonacci.fibs.take(10).force ==
      LazyList(0, 1, 1, 2, 3, 5, 8, 13, 21, 34))
  }
