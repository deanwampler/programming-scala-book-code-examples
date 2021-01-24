// src/main/scala/progscala3/basicoop/people/ZipCode.scala
package progscala3.basicoop.people

/**
 * US Zip Code. The constructor is private so that you have to use
 * one of the companion object apply methods, all of which do validation.
 * This example is not included in the book.
 */
case class ZipCode private (zip: Int, extension: Int):
  override def toString =
    if extension != 0 then s"$zip-$extension" else zip.toString

object ZipCode:

  def apply(zip: Int): ZipCode =
    doApply(validUSPSInts(zip, 0))
  def apply(zip: Int, extension: Int): ZipCode =
    doApply(validUSPSInts(zip, extension))
  def apply(zip: String): ZipCode =
    doApply(validUSPSStrs(zip, ""))
  def apply(zip: String, extension: String): ZipCode =
    doApply(validUSPSStrs(zip, extension))

  protected def doApply(
    zipExt: Either[String,(Int,Int)]): ZipCode =
    zipExt match
      case Right((z, e)) => new ZipCode(z, e)
      case Left(err)     => error(err)

  /**
   * Note: Real US Post Service zip codes aren't defined for all
   * integers from 10000 to 99999, exactly 5 digits. Same for
   * the extensions: empty, 0, OR exactly 4 digits (1000-9999).
   */
  protected def validUSPSInts(z: Int, e: Int): Either[String, (Int, Int)] =
    val validZip = z >= 10000 && z <= 99999
    val validExt = (e == 0 || e >= 1000) && e <=  9999
    (validZip, validExt) match
      case (true,  true)  => Right((z, e))
      case (false, true)  => Left(badZ(z))
      case (true,  false) => Left(badE(e))
      case (false, false) => Left(badZ(z) + " " + badE(e))

  // arbitrary outside white space is fine.
  protected val zipRE = """^\s*\d{5}\s*$""".r
  protected val extRE = """^\s*\d{4}\s*$""".r

  protected def validUSPSStrs(
      z: String, e: String): Either[String, (Int, Int)] =
    (z.trim, e.trim) match
      case (zipRE(), "")      => Right((z.toInt, 0))
      case (zipRE(), "0")     => Right((z.toInt, 0))
      case (zipRE(), extRE()) => Right((z.toInt, e.toInt))
      case (_, extRE())       => Left(badZ(z))
      case (zipRE(), _)       => Left(badE(e))
      case (_, _)             => Left(badZ(z) + " " + badE(e))

  protected def badZ(zip:String):String = s"Invalid zip $zip."
  protected def badZ(zip:Int):String    = s"Invalid zip $zip."
  protected def badE(ext:String):String = s"Invalid extension $ext."
  protected def badE(ext:Int):String    = s"Invalid extension $ext."
  protected def error(msg: String) = throw IllegalArgumentException(msg)
