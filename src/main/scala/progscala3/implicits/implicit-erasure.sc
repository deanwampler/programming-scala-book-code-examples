// src/main/scala/progscala3/implicits/implicit-erasure.sc
object M {
  implicit object IntMarker                                          // <1>
  implicit object StringMarker

  def p[T](t: T): String =                                           // <2>
    t.toString.split("\\$").filter(_.contains("Marker")).head

  def m(seq: Seq[Int])(implicit i: IntMarker.type): Unit =           // <3>
    println(s"Seq[Int]: $seq (implicit: ${p(i)})")

  def m(seq: Seq[String])(implicit s: StringMarker.type): Unit =     // <4>
    println(s"Seq[String]: $seq (implicit: ${p(s)})")
}

import M._                                                           // <5>
m(List(1,2,3))
m(List("one", "two", "three"))

