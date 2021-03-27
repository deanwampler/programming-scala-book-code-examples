// src/main/scala/progscala3/dsls/payroll/Money.scala
package progscala3.dsls.payroll
import progscala3.contexts.accounting.*                         // <1>
import scala.util.FromDigits.Floating                           // <2>

given Floating[Dollars] with                                    // <3>
  def fromDigits(digits: String): Dollars = Dollars(digits.toDouble)

given Floating[Percentage] with
  def fromDigits(digits: String): Percentage = Percentage(digits.toDouble)

implicit class dsc(sc: StringContext):                          // <4>
  def $(tokens: Any*) =
    val str = StringContextUtil.foldTokens(tokens.toSeq, sc.parts)
    Dollars(str.toDouble)

extension (amount: Double)                                      // <5>
  def dollars: Dollars = Dollars(amount)
  def percent: Percentage = Percentage(amount)

object StringContextUtil:                                       // <6>
  def foldTokens(tokens: Seq[Any], parts: Seq[String]): String =
    val (str, toks) = parts.foldLeft("" -> tokens.toSeq){
      case ((s, toks), s2) =>
        if s2 == null || s2.length == 0 then s+toks.head -> toks.tail
        else s+s2 -> toks
    }
    assert(toks.size == 0)
    str
