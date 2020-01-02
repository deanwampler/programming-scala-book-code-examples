// src/main/scala/progscala3/objectsystem/linearization/value-class-universal-traits.sc

trait M extends Any {
  def m(): String = "M "
}

trait Digitizer extends Any with M {
  override def m(): String = { "Digitizer " + super.m }

  def digits(s: String): String = s.replaceAll("""\D""", "")
}

trait Formatter extends Any with M {   
  override def m(): String = { "Formatter " + super.m }

  def format(areaCode: String, exchange: String, subnumber: String): String =
    s"($areaCode) $exchange-$subnumber"
}

// Split "exetnds AnyVal" to tell the REPL the expression crosses 2 lines:
class USPhoneNumber(val s: String) extends 
    AnyVal with Digitizer with Formatter{
  override def m(): String = { "USPhoneNumber " + super.m }
  
  override def toString = {
    val digs = digits(s)
    val areaCode = digs.substring(0,3)
    val exchange = digs.substring(3,6)
    val subnumber  = digs.substring(6,10)
    format(areaCode, exchange, subnumber)
  }
}

val number = new USPhoneNumber("987-654-3210")
assert(number.toString == "(987) 654-3210")
assert(number.m() == "USPhoneNumber Formatter Digitizer M ")
