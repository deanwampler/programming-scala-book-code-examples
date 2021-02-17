// src/test/scala/progscala3/objectsystem/linearization/PhoneNumberSuite.scala
package progscala3.objectsystem.linearization

import munit.*

class PHoneNumberSuite extends FunSuite:

  val pn = NAPhoneNumber("987-654-3210")

  test("NAPhoneNumber.toString returns the number in a canonical format") {
    assert(pn.toString == "(987) 654-3210")
  }
  test("NAPhoneNumber.m() returns the linearization") {
    assert(pn.m() == "NAPhoneNumber Formatter Digitizer M ")
  }
