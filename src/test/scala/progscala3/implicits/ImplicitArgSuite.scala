// src/test/scala/progscala3/implicits/ImplicitArgSuite.scala
package progscala3.implicits

import munit._
import scala.language.implicitConversions

class ImplicitArgSuite extends FunSuite {

  // Never use Floats for money:
  def calcTax(amount: Float)(implicit rate: Float): Float = amount * rate

  object SimpleStateSalesTax {
    implicit val rate: Float = 0.05F
  }

  case class ComplicatedSalesTaxData(
    baseRate: Float,
    isTaxHoliday: Boolean,
    storeId: Int)

  object ComplicatedSalesTax {
    private def extraTaxRateForStore(id: Int): Float = {
      // From id, determine location, then extra taxes...
      0.0F
    }

    implicit def rate(implicit cstd: ComplicatedSalesTaxData): Float =
      if cstd.isTaxHoliday then 0.0F
      else cstd.baseRate + extraTaxRateForStore(cstd.storeId)
  }

  test("An implicit paramater is filled by an in-scope value") {
    import SimpleStateSalesTax.rate

    val amount = 100F
    val tax = calcTax(amount)
    assert(tax == 5.0)
  }

  test("An implicit paramater is filled by an in-scope method call") {
    import ComplicatedSalesTax.rate
    implicit val myStore = ComplicatedSalesTaxData(0.06F, false, 1010)

    val amount = 100F
    val tax = calcTax(amount)
    assert(tax == 6.0)
  }
}
