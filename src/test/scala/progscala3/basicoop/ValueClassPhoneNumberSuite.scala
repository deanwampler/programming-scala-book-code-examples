// src/test/scala/progscala3/basicoop/ValueClassPhoneNumberSuite.scala
package progscala3.basicoop

import munit.*

class NAPhoneNumberSuite extends FunSuite:

  test("NAPhoneNumber.toString returns a human-readable string for the number") {
    val num = NAPhoneNumber("987-654-3210")
    assert(num.toString == "(987) 654-3210")
  }
