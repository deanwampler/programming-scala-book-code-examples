// src/main/scala/progscala3/basicoop/ValueClassPhoneNumberSuite.scala
package progscala3.basicoop

import progscala3.metaprogramming.require

class USPhoneNumberSuite extends progscala3.FunSuite2 {

  test("USPhoneNumber.toString returns a human-readable string for the number") {
    val num = new USPhoneNumber("987-654-3210")
    require(num.toString == "(987) 654-3210")
  }
}
