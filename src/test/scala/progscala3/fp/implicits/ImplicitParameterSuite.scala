// src/test/scala/progscala3/fp/implicits/ImplicitParameterSuite.scala
package progscala3.fp.implicits

import munit._
import scala.language.implicitConversions

class ImplicitParameterSuite extends FunSuite {

  def multiplier(i: Int)(implicit factor: Int): Int = {
    i * factor
  }

  test("Implicit parameters can be inferred from an implicit value") {
    implicit val factor: Int = 2
    assert(multiplier(2) == 4)
  }

  test("Implicit parameters can be inferred from an implicit method") {
    implicit def factor: Int = 3
    assert(multiplier(2) == 6)
  }

  test("Implicit parameters can be provided explicitly") {
    assert(multiplier(2)(4) == 8)
  }
}
