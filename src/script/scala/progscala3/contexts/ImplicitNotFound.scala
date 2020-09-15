// src/script/scala/progscala3/contexts/ImplicitNotFound.scala
// As of September 2020, this annotation appears to have no effect in Dotty
// for method argument annotations, just for types.

import scala.annotation.implicitNotFound

@implicitNotFound("Stringer: No implicit found ${T} : Tagify[${T}]")
trait Tagify[T]:
  def toTag(t: T): String

case class Stringer[T : Tagify](t: T):
  override def toString: String =
    s"Stringer: ${implicitly[Tagify[T]].toTag(t)}"

object O:
  def makeXML[T](t: T)(
      implicit @implicitNotFound("No Tagify[${T}] implicit found")
        tagger: Tagify[T]): String =
    s"<xml>${tagger.toTag(t)}</xml>"

given Tagify[Int]:
  def toTag(i: Int): String = s"<int>$i</int>"
given Tagify[String]:
  def toTag(s: String): String = s"<string>$s</string>"

Stringer("Hello World!")
Stringer(100)
O.makeXML("Hello World!")
O.makeXML(100)
Stringer(3.14569)
O.makeXML(3.14569)
