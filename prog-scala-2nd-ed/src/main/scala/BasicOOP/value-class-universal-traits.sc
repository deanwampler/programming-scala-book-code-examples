// src/main/scala/BasicOOP/value-class-universal-traits.sc

trait AllDigits extends Any {
  def digits(s: String): String = s.replaceAll("""\D""", "")   // <1>
}

trait Formatter extends Any {             // <2>
  def format(areaCode: String, exchange: String, whatsit: String): String =
    s"($areaCode) $exchange-$whatsit"
}

class USPhoneNumber(val s: String) extends AnyVal 
    with AllDigits with Formatter {

  override def toString = {
    val digs = digits(s)
    val areaCode = digs.substring(0,3)
    val exchange = digs.substring(3,6)
    val whatsit  = digs.substring(6,10)
    format(areaCode, exchange, whatsit)   // <3>
  }
}

val number = new USPhoneNumber("987-654-3210")
// Result: number: USPhoneNumber = (987) 654-3210
