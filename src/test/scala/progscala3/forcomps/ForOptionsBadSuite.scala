// src/test/scala/progscala3/forcomps/ForOptionsBadSuite.scala
package progscala3.forcomps

import munit.*

/**
 * A "bad" way to process a sequence of options, because it's unnecessarily
 * verbose. We stop on the first None. Compare with the for-option-good.sc
 * implementation. Note that the +return+ keyword is rarely used in Scala code.
 .* When you see one, treat it as a _design smell_, a possible candidate for
 * refactoring. Often, it can be eliminated by decomposing a function into
 * smaller functions.
 */
class ForOptionsBadSuite extends FunSuite:
  def doThreeSteps(
      step1: Int => Option[Int],
      step2: Int => Option[Int],
      step3: Int => Option[Int]): Option[Int] =
    val result1 = step1(0) match
      case None => return None
      case Some(result) => result
    val result2 = step2(result1) match
      case None => return None
      case Some(result) => result
    step3(result2)

  test("Working code with Options that is unnecessarily verbose") {
    val result1 = doThreeSteps(
      i1 => Some(i1 + 5),
      i2 => Some(i2 + 10),
      i3 => Some(i3 + 25))
    assert(result1 == Some(40))

    val result2 = doThreeSteps(
      i1 => Some(i1 + 5),
      i2 => None,   // EPIC FAIL!
      i3 => Some(i3 + 25))
    assert(result2 == None)
  }
