// src/test/scala/progscala3/contexts/accounting/NewImplicitConversionsSuite.scala
package progscala3.contexts.accounting
import scala.language.implicitConversions
import munit.*

// TODO: A ScalaCheck suite would be much better!
class NewImplicitConversionsSuite extends FunSuite:
  test("An implicit conversion will be invoked to convert between types") {
    given Conversion[Double,Dollars] = d => Dollars(d)
    given Conversion[Double,Percentage] = d => Percentage(d)

    val salary = Salary(100_000.0, 20.0)
    assert(salary.gross == Dollars(100_000.0))
    assert(salary.taxes == Percentage(20.0))
    assert(salary.net   == Dollars(80_000.0))
  }

  test("Dollars addition") {
    assert(Dollars(123.4) == Dollars(100.0) + Dollars(23.4))
  }
  test("Dollars subtraction") {
    assert(Dollars(100.0) == Dollars(123.4) - Dollars(23.4))
  }
  test("Dollars division by Double") {
    assert(Dollars(25.0) == Dollars(125.0) / 5.0)
  }
  test("Dollars multiplication by Percentage") {
    assert(Dollars(123.4) == Dollars(100.0) * Percentage(123.4))
  }

  test("Percentage addition") {
    assert(Percentage(23.4) == Percentage(10.0) + Percentage(13.4))
  }
  test("Percentage subtraction") {
    assert(Percentage(8.0) == Percentage(20.0) - Percentage(12.0))
  }
  test("Percentage multiplication") {
    assert(Percentage(0.25) == Percentage(125.0) * Percentage(20.0))
  }
  test("Percentage multiplication by Dollars") {
    assert(Dollars(125.0) == Percentage(125.0) * Dollars(100.0))
  }
