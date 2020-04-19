// src/test/scala/progscala3/implicits/ImplicitErasureWorkaroundSuite.scala
package progscala3.implicits

import munit._

class ImplicitErasureWorkaroundSuite extends FunSuite {
  object M {
    implicit object IntMarker                                    // <1>
    implicit object StringMarker

    def p[T](t: T): String =                                     // <2>
      t.toString.split("\\$").filter(_.contains("Marker")).head

    def m(seq: Seq[Int])(
        implicit i: IntMarker.type): (Seq[Int], String) =        // <3>
      (seq, s"Seq[Int]: $seq (implicit: ${p(i)})")

    def m(seq: Seq[String])(
        implicit s: StringMarker.type): (Seq[String], String) =  // <4>
      (seq, s"Seq[String]: $seq (implicit: ${p(s)})")
  }

  import M._                                                     // <5>

  test("To work around erasure, use an implicit arg of the right type") {
    assert(m(List(1,2,3))._2 ==
      "Seq[Int]: List(1, 2, 3) (implicit: IntMarker)")
    assert(m(List("one", "two", "three"))._2 ==
      "Seq[String]: List(one, two, three) (implicit: StringMarker)")
  }
}
