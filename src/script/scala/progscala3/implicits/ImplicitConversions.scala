// src/script/scala/progscala3/implicits/ImplicitConversions.scala

case class IntegralString(val str: String):
  private val intRE = """^[0-9]+$""".r
  /**
   * Does the string consist only of one or more, base 10 digits 0-9?
   * If true, then calling `toInt` or `toLong` will work, unless there
   * are too many digits.
   */
  def isIntegral: Boolean = intRE.matches(str)

implicit def toIntegralString(s: String): IntegralString = IntegralString(s)

assert("scala".isIntegral == false)
assert("12345".isIntegral == true)
