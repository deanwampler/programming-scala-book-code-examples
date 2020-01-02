// src/test/scala/progscala3/objectsystem/linearization/ValueClassSpec.scala
package progscala3.objectsystem.linearization
import org.scalatest.FunSpec

class ValueClassSpec extends FunSpec {

  val pn = USPhoneNumber("987-654-3210")

  describe ("USPhoneNumber") {
    describe ("toString") {
      it ("returns the number in a canonical format") {
        assert(pn.toString == "(987) 654-3210")
      }
    }
    describe("m()") {
      it ("returns the linearization") {
        assert(pn.m() == "USPhoneNumber Formatter Digitizer M ")
      }
    }
  }
}
