// src/main/scala/progscala3/objectsystem/linearization/PhoneNumber.scala
package progscala3.objectsystem.linearization

trait M extends Any:
  def m(): String = "M "

trait Digitizer extends Any with M:
  override def m(): String = "Digitizer " + super.m()

  def digits(s: String): String = s.replaceAll("""\D""", "")

trait Formatter extends Any with M:
  override def m(): String = "Formatter " + super.m()

  def format(
    areaCode: String, exchange: String, subnumber: String): String =
    s"($areaCode) $exchange-$subnumber"

case class NAPhoneNumber(s: String)
    extends AnyVal with Digitizer with Formatter:

  /**
   * Returns "NAPhoneNumber Formatter Digitizer M "
   */
  override def m(): String = "NAPhoneNumber " + super.m()

  /**
   * Return the string representation.
   * For a NAPhoneNumber("987-654-3210"), returns "(987) 654-3210"
   */
  override def toString =
    val digs = digits(s)
    val areaCode = digs.substring(0,3)
    val exchange = digs.substring(3,6)
    val subnumber  = digs.substring(6,10)
    format(areaCode, exchange, subnumber)
