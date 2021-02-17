// src/test/scala/progscala3/fp/datastructs/FoldLeftRightSuite.scala
package progscala3.fp.datastructs

import munit.*
import scala.annotation.tailrec

class FoldLeftRightSuite extends FunSuite:
  import FoldLeftRight.*

  val seq = Seq(1,2,3,4,5,6)

  test("foldLeft goes left to right") {
    assert(foldLeft(seq)("()")((accum, i) => s"($accum $i)") ==
      "((((((() 1) 2) 3) 4) 5) 6)")
  }
  test("foldRight goes right to left") {
    assert(foldRight(seq)("()")((i, accum) => s"($i $accum)") ==
      "(1 (2 (3 (4 (5 (6 ()))))))")
  }
