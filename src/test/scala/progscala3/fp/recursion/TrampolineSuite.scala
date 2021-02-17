// src/test/scala/progscala3/fp/recursion/TrampolineSuite.scala
package progscala3.fp.recursion

import munit.*
import scala.util.control.TailCalls.*

// Adapted from:
// https://www.scala-lang.org/api/current/scala/util/control/TailCalls$.html
class TrampolineSuite extends FunSuite:

  def isEven(xs: Seq[Int]): TailRec[Boolean] =
    if xs.isEmpty then done(true) else tailcall(isOdd(xs.tail))

  def isOdd(xs: Seq[Int]): TailRec[Boolean] =
   if xs.isEmpty then done(false) else tailcall(isEven(xs.tail))

  test("Trampolines can be used to reduce stack frame usage") {
    val eo = (1 to 5).map(i => (i, isEven(1 to i).result))
    assert(eo == Seq(1 -> false, 2 -> true, 3 -> false, 4 -> true, 5 -> false))
  }
