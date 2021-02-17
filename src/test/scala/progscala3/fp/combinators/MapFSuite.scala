// src/test/scala/progscala3/fp/combinators/MapFSuite.scala
package progscala3.fp.combinators

import munit.*
import scala.language.higherKinds                         // <1>

class MapFSuite extends FunSuite:

  object MapF {
    def map[A,B](f: (A) => B)(list: List[A]): List[B] = list map f
  }

  val intToString = (i:Int) => s"N=$i"
  val input = List(1, 2, 3, 4)
  val expected = List[String]("N=1", "N=2", "N=3", "N=4")

  test("A function-first combinator lifts a function to a Seq=>Seq function") {
    val f = MapF.map(intToString)
    val list = f(input)
    assert(list == expected)
  }
