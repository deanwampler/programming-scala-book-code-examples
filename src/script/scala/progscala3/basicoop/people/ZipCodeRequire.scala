// src/script/scala/progscala3/basicoop/people/ZipCodeRequire.scala

// This example is no longer mentioned in the book. It demonstrates using
// Predef.require to validate inputs. See the Design by Contract discussion
// in chapter 24.
case class ZipCode (zip: Int, extension: Int):             // <1>
  require(zip > 0 && zip < 100000, s"zip $zip is invalid")
  require(extension >= 0 && extension < 10000,
    s"extension $extension is invalid")
  override def toString =
    if extension != 0 then s"$zip-$extension" else zip.toString
