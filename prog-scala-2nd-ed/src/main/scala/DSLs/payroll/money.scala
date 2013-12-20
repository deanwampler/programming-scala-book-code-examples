// code-examples/DSLs/payroll/money.scala

package dsls.payroll
import java.math.{BigDecimal => JBigDecimal, 
    MathContext => JMathContext, RoundingMode => JRoundingMode}

/** Most arithmetic is done using JBigDecimals for tighter control.
 */
class Money(val amount: BigDecimal) {

  def + (m: Money)  = 
      Money(amount.bigDecimal.add(m.amount.bigDecimal))
  def - (m: Money)  = 
      Money(amount.bigDecimal.subtract(m.amount.bigDecimal))
  def * (m: Money)  = 
      Money(amount.bigDecimal.multiply(m.amount.bigDecimal))
  def / (m: Money)  = 
      Money(amount.bigDecimal.divide(m.amount.bigDecimal, 
          Money.scale, Money.jroundingMode))

  def <  (m: Money)  = amount <  m.amount
  def <= (m: Money)  = amount <= m.amount
  def >  (m: Money)  = amount >  m.amount
  def >= (m: Money)  = amount >= m.amount

  override def equals (o: Any) = o match {
    case m: Money => amount equals m.amount
    case _ => false
  }
  
  override def hashCode = amount.hashCode * 31

  // Hack: Must explicitly call the correct conversion: double2Double
  override def toString = 
      String.format("$%.2f", double2Double(amount.doubleValue))
}

object Money {
  def apply(amount: BigDecimal)  = new Money(amount)
  def apply(amount: JBigDecimal) = new Money(scaled(new BigDecimal(amount)))
  def apply(amount: Double)      = new Money(scaled(BigDecimal(amount)))
  def apply(amount: Long)        = new Money(scaled(BigDecimal(amount)))
  def apply(amount: Int)         = new Money(scaled(BigDecimal(amount)))

  def unapply(m: Money) = Some(m.amount)
  
  protected def scaled(d: BigDecimal) = d.setScale(scale, roundingMode)
  
  val scale = 4
  val jroundingMode = JRoundingMode.HALF_UP
  val roundingMode  = BigDecimal.RoundingMode.HALF_UP
  val context = new JMathContext(scale, jroundingMode)
}

object Type2Money {
  implicit def bigDecimal2Money(b: BigDecimal)   = Money(b)
  implicit def jBigDecimal2Money(b: JBigDecimal) = Money(b)
  implicit def double2Money(d: Double)           = Money(d)
  implicit def long2Money(l: Long)               = Money(l)
  implicit def int2Money(i: Int)                 = Money(i)
}
