// src/main/scala/progscala3/basicoop/ValueClassUniversalTraitsSpec.scala
package progscala3.basicoop
import org.scalatest.FunSpec
import org.scalactic.source.Position.here

class ValueClassUniversalTraitsSpec extends FunSpec {

  describe ("USPhoneNumber1UTtoString") {
    it ("returns a human-readable string for the number") {
      val num = new USPhoneNumberUT("987-654-3210")
      assert(num.toString == "(987) 654-3210")
    }
  }
}
