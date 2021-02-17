// src/test/scala/progscala3/forcomps/ForVariableTranslatedSuite.scala
package progscala3.forcomps

import munit.*

class ForVariableTranslatedSuite extends FunSuite:

  val map = Map("one" -> 1, "two" -> 2)

  test("Variables can be assigned translated values in for comprehensions") {
    val list = for
      (key, value) <- map   // How are this line and the next one translated?
      i10 = value + 10
    yield (i10)
    assert(list == List(11, 12))
  }

  test("Variables translations are map steps") {
    val list = for
      (i, i10) <- for
        x1 @ (key, value) <- map
      yield {
        val x2 @ i10 = value + 10
        (x1, x2)
      }
    yield (i10)
    assert(list == List(11, 12))
  }
