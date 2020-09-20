// src/script/scala/progscala3/basicoop/people/ZipCodeRequire.scala

case class ZipCode (zip: Int, extension: Int):             // <1>
  require(zip > 0 && zip < 100000, s"zip $zip is invalid")
  require(extension >= 0 && extension < 10000,
    s"extension $extension is invalid")
  override def toString =
    if extension != 0 then s"$zip-$extension" else zip.toString
