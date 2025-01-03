// tag::definitions[]
// src/script/scala/progscala3/contexts/UsingTypeErasureWorkaround.scala

object O2:
  trait Marker[T]                                               // <1>
  // In Scala 3.0.0, the following has to be written:
  // given IntMarker: Marker[Int]: {}
  given IntMarker: Marker[Int]()
  given StringMarker: Marker[String]()

  def m(is: Seq[Int])(using IntMarker.type): Int = is.sum       // <2>
  def m(ss: Seq[String])(using StringMarker.type): Int = ss.length
// end::definitions[]

import O2.{given, *}

m(Seq(1,2,3))
m(Seq("one", "two", "three"))
