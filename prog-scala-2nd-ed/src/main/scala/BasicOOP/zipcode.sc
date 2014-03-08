// src/main/scala/BasicOOP/zipcode.sc

case class ZipCode(zip: Int, extension: Option[Int] = None) {
  require(valid(zip, extension),                              // <1>
    s"Invalid Zip+4 specified: $toString")

  protected def valid(z: Int, e: Option[Int]): Boolean = {
    if (0 < z && z <= 99999) e match {
      case None    => validUSPS(z, 0)
      case Some(e) => 0 < e && e <= 9999 && validUSPS(z, e)
    } 
    else false
  }

  /** Is it a real US Postal Service zip code? */
  protected def validUSPS(i: Int, e: Int): Boolean = true     // <2>

  override def toString =                                     // <3>
    if (extension != None) s"$zip-${extension.get}" else zip.toString
}

object ZipCode {
  def apply (zip: Int, extension: Int): ZipCode = 
    new ZipCode(zip, Some(extension))
}

ZipCode(12345)
// Result: ZipCode = 12345

ZipCode(12345, Some(6789))
// Result: ZipCode = 12345-6789

ZipCode(12345, 6789)
// Result: ZipCode = 12345-6789

ZipCode(0, 6789)
// Result: java.lang.IllegalArgumentException: requirement failed: 
//   Invalid Zip+4 specified: 0-6789

ZipCode(12345, 0)
// Result: java.lang.IllegalArgumentException: requirement failed: 
//   Invalid Zip+4 specified: 12345-0
