// src/test/scala/progscala3/objectsystem/linearization/ValueClassSuite.scala
package progscala3.objectsystem.linearization

import scala.language.implicitConversions
import munit._
import progscala3.metaprogramming.require

class ValueClassSuite extends FunSuite {

  val pn = USPhoneNumber("987-654-3210")

  test("USPhoneNumber.toString returns the number in a canonical format") {
    require(pn.toString == "(987) 654-3210")
  }
  test("USPhoneNumber.m() returns the linearization") {
    require(pn.m() == "USPhoneNumber Formatter Digitizer M ")
  }
}
