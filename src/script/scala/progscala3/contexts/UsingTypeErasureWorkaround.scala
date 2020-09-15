// src/script/scala/progscala3/contexts/UsingTypeErasureWorkaround.scala

object M:
  implicit object IntMarker                                // <1>
  implicit object StringMarker

  def m(seq: Seq[Int])(using IntMarker.type): String =     // <2>
    s"Seq[Int]: $seq"

  def m(seq: Seq[String])(using StringMarker.type): String =
    s"Seq[String]: $seq"

import M._

m(Seq(1,2,3))
m(Seq("one", "two", "three"))
m(Seq("one" -> 1, "two" -> 2, "three" -> 3))   // ERROR
