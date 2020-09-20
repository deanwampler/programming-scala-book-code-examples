// src/script/scala/progscala3/basicoop/people/ZipCodeRequire.scala

case class ZipCode (zip: Int, extension: Int):             // <1>
  require(zip > 0 && zip < 100000 && extension >= 0 && extension < 10000)
  override def toString =
    if extension != 0 then s"$zip-$extension" else zip.toString
