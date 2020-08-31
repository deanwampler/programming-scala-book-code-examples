// src/script/scala/progscala3/implicits/ImplicitErasureWorkaround.scala

object M:
  implicit object IntMarker                                    // <1>
  implicit object StringMarker

  def p[T](t: T): String =                                     // <2>
    t.toString.split("\\$").filter(_.contains("Marker")).head

  def m(seq: Seq[Int])(implicit i: IntMarker.type): String =   // <3>
    s"Seq[Int]: $seq (implicit: ${p(i)})"

  def m(seq: Seq[String])(implicit s: StringMarker.type): String =
    s"Seq[String]: $seq (implicit: ${p(s)})"

import M._

m(Seq(1,2,3))
m(Seq("one", "two", "three"))
m(Seq("one" -> 1, "two" -> 2, "three" -> 3))   // ERROR
