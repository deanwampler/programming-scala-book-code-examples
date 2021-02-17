// src/test/scala/progscala3/meta/InvariantSuite.scala
package progscala3.meta

import munit.*

class InvariantSuite extends FunSuite:
  case class Variable(var i: Int, var s: String)

  def succeed() =                                                  // <1>
    val v = Variable(0, "Hello!")
    val i1 = invariant(v.s == "Hello!") {                          // <2>
      v.i += 1
      v.i += 1
      v.i
    }
    assert(i1 == 2)

  test("invariant.apply should not fail if the invariant holds") { succeed() }

  test("invariant.apply should return the value returned by the expressions") { succeed() }

  test("invariant.apply should fail if the invariant is broken") {
    intercept[invariant.InvariantFailure] {                        // <3>
      val v = Variable(0, "Hello!")
      invariant(v.s == "Hello!") {
        v.i += 1
        v.s = "Goodbye!"
        v.i += 1
      }
    }
  }
