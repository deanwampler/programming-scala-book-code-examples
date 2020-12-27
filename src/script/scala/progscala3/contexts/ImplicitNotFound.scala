// tag::definitions[]
// src/script/scala/progscala3/contexts/ImplicitNotFound.scala

import scala.annotation.implicitNotFound

@implicitNotFound("No implicit found: Tagify[${T}]")
trait Tagify[T]:
  def toTag(t: T): String

case class Stringer[T : Tagify](t: T):
  override def toString: String =
    s"Stringer: ${summon[Tagify[T]].toTag(t)}"

object O:
  def makeXML[T](t: T)(
      using @implicitNotFound("makeXML: No Tagify[${T}] implicit found")
        tagger: Tagify[T]): String =
    s"<xml>${tagger.toTag(t)}</xml>"
// end::definitions[]

// tag::usage[]
given Tagify[Int] with
  def toTag(i: Int): String = s"<int>$i</int>"
given Tagify[String] with
  def toTag(s: String): String = s"<string>$s</string>"

Stringer("Hello World!")
Stringer(100)
O.makeXML("Hello World!")
O.makeXML(100)

Stringer(3.14569)       // ERROR: No implicit found
O.makeXML(3.14569)      // ERROR: No implicit found
// end::usage[]
