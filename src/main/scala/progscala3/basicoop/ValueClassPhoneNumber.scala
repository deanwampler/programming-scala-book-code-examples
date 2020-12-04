// src/main/scala/progscala3/basicoop/ValueClassPhoneNumber.scala
package progscala3.basicoop

class NAPhoneNumber(val s: String) extends AnyVal:    // <1>
  override def toString =
    val digs = digits(s)
    val areaCode  = digs.substring(0,3)
    val exchange  = digs.substring(3,6)
    val subnumber = digs.substring(6,10)  // "subscriber number"
    s"($areaCode) $exchange-$subnumber"

  override def equals(other: Any): Boolean = other match
    case p: NAPhoneNumber => digits(s).equals(digits(p.s))
    case s2: String => digits(s).equals(digits(s2))
    case _ => false

  private def digits(str: String): String = str.replaceAll("""\D""", "")
