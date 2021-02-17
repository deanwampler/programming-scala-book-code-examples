// src/test/scala/progscala3/fp/basics/HOFsClosures2Suite.scala
package progscala3.fp.basics

import munit.*

class HOFsClosures2Suite extends FunSuite:

  test("Variables referenced by closures can be in objects") {
    object Multiplier:
      var factor = 2

    val multiplier = (i: Int) => i * Multiplier.factor

    val result1 =
      (1 to 10) filter (_ % 2 == 0) map multiplier reduce (_ * _)
    assert(result1 == 122880)

    Multiplier.factor = 3
    val result2 =
      (1 to 10) filter (_ % 2 == 0) map multiplier reduce (_ * _)
    assert(result2 == 933120)
  }

  test("Object methods can be used as functions") {
    object Multiplier2:
      var factor = 2
      // Compare: val multiplier = (i: Int) => i * factor
      def multiplier(i: Int) = i * factor

    val result1 =
      (1 to 10) filter (_ % 2 == 0) map Multiplier2.multiplier reduce (_ * _)
    assert(result1 == 122880)

    Multiplier2.factor = 3
    val result2 =
      (1 to 10) filter (_ % 2 == 0) map Multiplier2.multiplier reduce (_ * _)
    assert(result2 == 933120)
  }
