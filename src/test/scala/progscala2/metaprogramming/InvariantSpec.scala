// src/test/scala/progscala2/metaprogramming/InvariantSpec.scala
package metaprogramming
import reflect.runtime.universe._
import org.scalatest.FunSpec
 
class InvariantSpec extends FunSpec { 
  case class Variable(var i: Int, var s: String)

  describe ("invariant.apply") { 
    def succeed() = {                                                // <1>
      val v = Variable(0, "Hello!")
      val i1 = invariant(v.s == "Hello!") {                          // <2>
        v.i += 1
        v.i += 1
        v.i
      }
      assert (i1 === 2)
    }

    it ("should not fail if the invariant holds") { succeed() }

    it ("should return the value returned by the expressions") { succeed() }
  
    it ("should fail if the invariant is broken") {
      intercept[invariant.InvariantFailure] {                        // <3>
        val v = Variable(0, "Hello!")
        invariant(v.s == "Hello!") {
          v.i += 1
          v.s = "Goodbye!"
          v.i += 1
        }
      }
    }
  }
}
