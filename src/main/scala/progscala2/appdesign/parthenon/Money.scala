// src/main/scala/progscala2/appdesign/parthenon/Money.scala
package progscala2.appdesign.parthenon

/**
 * The same class from the Design by Contract section, with a new apply method
 * in the companion object below.
 */
case class Money(amount: Double) {
  require(amount >= 0.0, s"Negative amount $amount not allowed")

  def +  (m: Money): Money = Money(amount + m.amount)
  def -  (m: Money): Money = Money(amount - m.amount)
  def >= (m: Money): Boolean = amount >= m.amount

  override def toString = "$"+amount
}

case object Money {
  /**
   * Convenience method to convert a string to Money. Doesn't handle an invalid
   * string!
   */
  def apply(amount: String): Money = Money(amount.toDouble)
}
