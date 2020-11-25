// src/main/scala/progscala3/dsls/payroll/Money.scala
package progscala3.dsls.payroll
import scala.annotation.targetName

sealed trait Amount:                                            // <1>
  def amount: Double
  protected def format(value: Double): String =
    val left = math.floor(value).toInt
    val right = math.round((value - left)*100).toInt
    f"$left.$right%02d"

case class Percentage(amount: Double) extends Amount:           // <2>
  override def toString = s"${format(amount)}%"

case class Dollars(amount: Double) extends Amount:              // <3>
  override def toString = s"$$${format(amount)}"

import scala.util.FromDigits.Floating                           // <4>

given Floating[Percentage]:                                     // <5>
  def fromDigits(digits: String): Percentage = Percentage(digits.toDouble)

given Floating[Dollars]:                                        // <6>
  def fromDigits(digits: String): Dollars = Dollars(digits.toDouble)

implicit class dsc(sc: StringContext):                          // <7>
  @targetName("dollars") def $(tokens: Any*) =
    val str = StringContextUtil.foldTokens(tokens.toSeq, sc.parts)
    new Dollars(str.toDouble)

extension (amount: Double):                                     // <8>
  def percent: Percentage = Percentage(amount)
  def dollars: Dollars = Dollars(amount)

object StringContextUtil:                                       // <9>
  def foldTokens(tokens: Seq[Any], parts: Seq[String]): String =
    val (str, toks) = parts.foldLeft("" -> tokens.toSeq){
      case ((s, toks), s2) =>
        if s2 == null || s2.length == 0 then s+toks.head -> toks.tail
        else s+s2 -> toks
    }
    assert(toks.size == 0)
    str
