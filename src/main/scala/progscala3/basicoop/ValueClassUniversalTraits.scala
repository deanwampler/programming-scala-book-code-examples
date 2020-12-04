// src/main/scala/progscala3/basicoop/ValueClassUniversalTraits.scala
package progscala3.basicoop

trait Digitizer extends Any:
  def digits(s: String): String = s.replaceAll("""\D""", "")         // <1>

trait Formatter extends Any:                                         // <2>
  def format(
      areaCode: String, exchange: String, subnumber: String): String =
    s"($areaCode) $exchange-$subnumber"

case class NAPhoneNumberUT(s: String)
    extends AnyVal with Digitizer with Formatter:
  override def toString =
    val digs = digits(s)
    val areaCode = digs.substring(0,3)
    val exchange = digs.substring(3,6)
    val subnumber  = digs.substring(6,10)
    format(areaCode, exchange, subnumber)                            // <3>

  override def equals(other: Any): Boolean = other match
    case p: NAPhoneNumberUT => digits(s).equals(digits(p.s))
    case s2: String => digits(s).equals(digits(s2))
    case _ => false
