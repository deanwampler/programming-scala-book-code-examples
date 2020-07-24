// src/test/scala/progscala3/implicits/GenericExtensionMethodSuite.scala
package progscala3.implicits

import munit._
import scala.language.implicitConversions

class GenericExtensionMethodSuite extends FunSuite {

  def [A : Ordering](seq: Seq[A]) sortUnique: Seq[A] =
    if seq.size == 0
      seq
    else
      val sorted = seq.sorted
      sorted.foldLeft(Vector(sorted.head)) { (vect, a) =>
        if vect.last != a
          vect :+ a
        else
          vect
      }

  test("An extension method can be defined for a parameterized type") {
    val items1 = Seq(5, 1, 3, 1, 2, 2, 4, 3, 1, 4, 5)
    println(items1.sortUnique)
    assert(items1.sortUnique == Seq(1, 2, 3, 4, 5))

    val items2 = items1.map(_.toString)
    assert(items2.sortUnique == Seq("1", "2", "3", "4", "5"))
  }
}
