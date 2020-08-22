// src/script/scala/progscala3/basicoop/people/ZipCodeRequire.scala

case class ZipCode (zip: Int, extension: Int):
  require(zip > 0 && zip < 100000 && extension < 10000)              // <1>
  override def toString =
    if extension != 0 then s"$zip-$extension" else zip.toString
