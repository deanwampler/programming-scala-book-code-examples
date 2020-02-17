// src/main/scala/progscala3/basicoop/ValueClassPhoneNumberSuite.scala
package progscala3.basicoop

import munit._
import progscala3.metaprogramming.requirement

class USPhoneNumberSuite extends FunSuite {

  test("USPhoneNumber.toString returns a human-readable string for the number") {
    val num = new USPhoneNumber("987-654-3210")
    requirement(num.toString == "(987) 654-3210")
  }
}
