// src/main/scala/progscala2/implicits/implicit-erasure.sc
object M {
  implicit object IntMarker                                          // <1>
  implicit object StringMarker

  def m(seq: Seq[Int])(implicit i: IntMarker.type): Unit =           // <2>
    println(s"Seq[Int]: $seq")

  def m(seq: Seq[String])(implicit s: StringMarker.type): Unit =     // <3>
    println(s"Seq[String]: $seq")
}

import M._                                                           // <4>
m(List(1,2,3))
m(List("one", "two", "three"))

