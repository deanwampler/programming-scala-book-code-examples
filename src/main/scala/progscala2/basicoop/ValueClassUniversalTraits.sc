// src/main/scala/progscala2/basicoop/ValueClassUniversalTraits.sc

trait Digitizer extends Any {
  def digits(s: String): String = s.replaceAll("""\D""", "")         // <1>
}

trait Formatter extends Any {                                        // <2>
  def format(areaCode: String, exchange: String, subnumber: String): String =
    s"($areaCode) $exchange-$subnumber"
}

class USPhoneNumber(val s: String) extends AnyVal 
    with Digitizer with Formatter {

  override def toString = {
    val digs = digits(s)
    val areaCode = digs.substring(0,3)
    val exchange = digs.substring(3,6)
    val subnumber  = digs.substring(6,10)
    format(areaCode, exchange, subnumber)                            // <3>
  }
}

val number = new USPhoneNumber("987-654-3210")
// Result: number: USPhoneNumber = (987) 654-3210
