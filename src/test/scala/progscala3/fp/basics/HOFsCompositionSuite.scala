// src/test/scala/progscala3/fp/basics/HOFsCompositionSuite.scala
package progscala3.fp.basics

import munit.*

class HOFsCompositionSuite extends FunSuite:

  test("Collections methods and their functions compose - operator syntax") {
    val result1 = (1 to 10) filter (_ % 2 == 0) map (_ * 2) reduce (_ * _)
    assert(result1 == 122880)
  }

  test("Collections methods and their functions compose - regular syntax") {
    val result2 = (1 to 10).filter(_ % 2 == 0).map(_ * 2).reduce(_ * _)
    assert(result2 == 122880)
  }
