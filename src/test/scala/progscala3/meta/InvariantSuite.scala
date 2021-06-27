// src/test/scala/progscala3/meta/InvariantSuite.scala
package progscala3.meta

import munit.*
import org.scalacheck.*

class InvariantSuite extends ScalaCheckSuite:
  import Prop.{forAll, propBoolean}

  case class Variable(var i: Int, var s: String)
  val genVariables =
    for
      i <- Gen.posNum[Int]
      s <- Arbitrary.arbitrary[String]
    yield Variable(i, s)

  protected def passingInvariant =
    forAll(genVariables) { variable =>
      val s = variable.s
      val i = variable.i
      val i2 = invariant(variable.s == s) {
        variable.i += 2
        variable.i
      }
      (i2 == i+2 && variable.s == s) :| s"i2=$i2, s=$s, variable = $variable"
    }

  property("invariant.apply should not fail if the invariant holds") { passingInvariant }
  property("invariant.apply should return the value returned by the expressions") { passingInvariant }
  property("invariant.apply should fail if the invariant is broken") {
    forAll(genVariables) { variable =>
      val s = variable.s
      intercept[invariant.InvariantFailure] {
        invariant(variable.s == s) {
          variable.i += 2
          variable.s = "bad"
        }
      }
      true // hack to make the types check.
    }
  }
