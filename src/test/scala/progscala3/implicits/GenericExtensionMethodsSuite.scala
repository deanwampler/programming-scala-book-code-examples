// src/test/scala/progscala3/implicits/GenericExtensionMethodsSuite.scala
package progscala3.implicits

import munit._
import GenericExtensionMethods._

class GenericExtensionMethodsSuite extends FunSuite:

  test("An extension method can be defined for a parameterized type") {
    val items1 = Seq(5, 1, 3, 1, 2, 2, 4, 3, 1, 4, 5)
    println(items1.sortedUnique)
    assert(items1.sortedUnique == Seq(1, 2, 3, 4, 5))

    val items2 = items1.map(_.toString)
    assert(items2.sortedUnique == Seq("1", "2", "3", "4", "5"))
  }
