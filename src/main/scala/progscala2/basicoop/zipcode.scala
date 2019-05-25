// src/main/scala/progscala2/basicoop/Zipcode.scala
package progscala2.basicoop

case class ZipCode(zip: String, extension: Option[String] = None) {
  require(validUSPS(zip, extension),                                     // <1>
    s"Invalid Zip+4 specified: $toString")

  protected val zipRE = """(\d){5}""".r
  protected val extRE = """(\d){4}""".r

  /**
   * Is it a real US Postal Service zip code?
   */
  protected def validUSPS(z: String, e: Option[String]): Boolean =   // <2>
    (z, e) match {
      case (zipRE(_), None) => true
      case (zipRE(_), Some(extRE(_))) => true
      case (_, _) => false
    }

  override def toString =                                            // <3>
    if (extension != None) s"$zip-${extension.get}" else zip.toString
}

object ZipCode {
  def apply (zip: String, extension: String): ZipCode =
    new ZipCode(zip, Some(extension))
}
