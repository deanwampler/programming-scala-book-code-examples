// src/main/scala/progscala3/basicoop/ValueClassPhoneNumberSuite.scala
package progscala3.basicoop

import scala.language.implicitConversions
import munit._
import progscala3.metaprogramming.require

class USPhoneNumberSuite extends FunSuite {

  test("USPhoneNumber.toString returns a human-readable string for the number") {
    val num = new USPhoneNumber("987-654-3210")
    require(num.toString == "(987) 654-3210")
  }
}
