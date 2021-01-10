// src/script/scala/progscala3/typelessdomore/MethodOverloadedReturn.scala

case class Money(value: BigDecimal)
case object Money:
  def apply(s: String): Money = apply(BigDecimal(s.toDouble))
  def apply(d: Double): Money = apply(BigDecimal(d))
