// src/test/scala/progscala3/fp/recursion/FactorialSuite.scala
package progscala3.fp.recursion

import munit.*
import scala.annotation.tailrec

class FactorialSuite extends FunSuite:

  /*
   * If you use the annotation `@tailrec` you get:
   * "error: could not optimize @tailrec annotated method factorial".
   * This is because it contains a recursive call not in tail position.
   */
  // @tailrec
  def factorial1(i: BigInt): BigInt =
    if i == 1 then i
    else i * factorial1(i - 1)

  /** A tail-recursive implementation */
  def factorial(i: BigInt): BigInt =
    @tailrec
    def fact(i: BigInt, accumulator: BigInt): BigInt =
      if i == 1 then accumulator
      else fact(i - 1, i * accumulator)
    fact(i, 1)

  test("A non tail-recursive function can blow the stack") {
    val facts = (1 to 5).map(i => (i, factorial1(i)))
    assert(facts == Seq(1 -> 1, 2 -> 2, 3 -> 6, 4 -> 24, 5 -> 120))
  }

  test("A tail-recursive function is optimized to reduce stack frames") {
    val facts = (1 to 5).map(i => (i, factorial(i)))
    assert(facts == Seq(1 -> 1, 2 -> 2, 3 -> 6, 4 -> 24, 5 -> 120))
  }
