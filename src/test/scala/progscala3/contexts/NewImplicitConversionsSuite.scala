// src/test/scala/progscala3/contexts/NewImplicitConversionsSuite.scala
package progscala3.contexts
import scala.language.implicitConversions
import munit._

class NewImplicitConversionsSuite extends FunSuite:
  test("An implicit conversion will be invoked to convert between types") {
    given Conversion[Double,Dollars] = d => Dollars(d)
    given Conversion[Double,Percentage] = d => Percentage(d)

    val salary = Salary(100_000.0, 0.20)
    assert(salary.gross == Dollars(100_000.0))
    assert(salary.taxes == Percentage(0.2))
    assert(salary.net   == Dollars(80_000.0))
  }
