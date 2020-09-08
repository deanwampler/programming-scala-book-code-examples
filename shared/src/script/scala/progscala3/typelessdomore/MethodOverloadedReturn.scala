// src/script/scala/progscala3/typelessdomore/MethodOverloadedReturn.scala

case class Money(value: Double)
case object Money {
  def apply(s: String): Money     = apply(s.toDouble)
  def apply(d: BigDecimal): Money = apply(d.toDouble)
}
