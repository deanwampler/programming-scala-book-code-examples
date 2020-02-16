// src/test/scala/progscala3/objectsystem/linearization/ValueClassSuite.scala
package progscala3.objectsystem.linearization

import progscala3.metaprogramming.require

class ValueClassSuite extends progscala3.FunSuite2 {

  val pn = USPhoneNumber("987-654-3210")

  test("USPhoneNumber.toString returns the number in a canonical format") {
    require(pn.toString == "(987) 654-3210")
  }
  test("USPhoneNumber.m() returns the linearization") {
    require(pn.m() == "USPhoneNumber Formatter Digitizer M ")
  }
}
