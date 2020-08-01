// src/test/scala/progscala3/objectsystem/linearization/PHoneNumberSuite.scala
package progscala3.objectsystem.linearization

import munit._

class PHoneNumberSuite extends FunSuite:

  val pn = USPhoneNumber("987-654-3210")

  test("USPhoneNumber.toString returns the number in a canonical format") {
    assert(pn.toString == "(987) 654-3210")
  }
  test("USPhoneNumber.m() returns the linearization") {
    assert(pn.m() == "USPhoneNumber Formatter Digitizer M ")
  }
