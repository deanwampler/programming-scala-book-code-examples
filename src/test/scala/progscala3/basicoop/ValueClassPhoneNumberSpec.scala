// src/main/scala/progscala3/basicoop/ValueClassPhoneNumberSpec.scala
package progscala3.basicoop
import org.scalatest.FunSpec
import scala.language.implicitConversions
import org.scalactic.source.Position.here

class USPhoneNumberSpec extends FunSpec {

  describe ("USPhoneNumber.toString") {
    it ("returns a human-readable string for the number") {
      val num = new USPhoneNumber("987-654-3210")
      assert(num.toString == "(987) 654-3210")
    }
  }
}
