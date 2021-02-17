// src/test/scala/progscala3/contexts/GenericExtensionMethodsSuite.scala
package progscala3.contexts

import munit.*
import GenericExtensionMethods.*

class GenericExtensionMethodsSuite extends FunSuite:

  test("An extension method can be defined for a parameterized type") {
    val items1 = Seq(5, 1, 3, 1, 2, 2, 4, 3, 1, 4, 5)
    assert(items1.sortedUnique == Seq(1, 2, 3, 4, 5), items1.sortedUnique.toString)

    val items2 = items1.map(_.toString)
    assert(items2.sortedUnique == Seq("1", "2", "3", "4", "5"))
  }
