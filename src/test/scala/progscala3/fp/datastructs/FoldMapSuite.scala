// src/test/scala/progscala3/fp/datastructs/FoldMapSuite.scala
package progscala3.fp.datastructs

import munit.*

class FoldMapSuite extends FunSuite:

  val vector = Vector(1, 2, 3, 4, 5, 6)

  test("Fold can be used to implement map") {
    val vector2 = vector.foldRight(Vector.empty[String]) {
      (x, vector) => ("[" + x + "]") +: vector
    }
    assert(vector2 == Vector("[1]", "[2]", "[3]", "[4]", "[5]", "[6]"))
  }

  test("Fold can be used to implement filter") {
    val vector2 = vector.foldRight(Vector.empty[Int]) {
      (x, vector) => if x % 2 == 0 then vector else x +: vector
    }
    assert(vector2 == Vector(1, 3, 5))
  }

  test("Fold can be used to implement flatMap") {
    val vector2 = vector.foldRight(Vector.empty[Seq[Int]]) {
      (x, vector) =>  (1 to x) +: vector
    }.flatten
    assert(vector2 ==
      Vector(1, 1, 2, 1, 2, 3, 1, 2, 3, 4, 1, 2, 3, 4, 5, 1, 2, 3, 4, 5, 6))
  }
