// src/test/scala/progscala3/objectsystem/linearization/ValueClassSuite.scala
package progscala3.objectsystem.linearization

import munit._

class ValueClassSuite extends FunSuite {

  val pn = USPhoneNumber("987-654-3210")

  test("USPhoneNumber.toString returns the number in a canonical format") {
    assert(pn.toString == "(987) 654-3210")
  }
  test("USPhoneNumber.m() returns the linearization") {
    assert(pn.m() == "USPhoneNumber Formatter Digitizer M ")
  }
}
