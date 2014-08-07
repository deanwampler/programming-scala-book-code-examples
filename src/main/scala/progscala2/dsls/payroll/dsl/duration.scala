// src/main/scala/progscala2/dsls/payroll/dsl/duration.scala

package progscala2.dsls.payroll.dsl

case class Duration(val amount: Int) {
  /** @return the number of work days in "amount" weeks. */
  def weeks = amount * 5

  /** @return the number of work days in "amount" years. */
  def years = amount * 260
}

