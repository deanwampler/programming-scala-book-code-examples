// src/main/scala/progscala3/basicoop/ValueClassPhoneNumber.scala
package progscala3.basicoop

class NAPhoneNumber(val s: String) extends AnyVal:    // <1>
  override def toString =
    val digs = digits(s)
    val areaCode  = digs.substring(0,3)
    val exchange  = digs.substring(3,6)
    val subnumber = digs.substring(6,10)  // "subscriber number"
    s"($areaCode) $exchange-$subnumber"

  private def digits(str: String): String = str.replaceAll("""\D""", "")
