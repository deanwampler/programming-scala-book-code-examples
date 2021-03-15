// src/test/scala/progscala3/fp/categories/FunctorSuite.scala
package progscala3.fp.categories

import munit.*
import scala.language.higherKinds                         // <1>

class FunctorSuite extends FunSuite:

  val fid: Int => Double    = i => 1.5 * i

  test("SeqF.map behaves like the collection's map") {
    assert(SeqF.map(Seq(1,2,3,4))(fid) == Seq(1.5, 3.0, 4.5, 6.0))
    assert(SeqF.map(Seq.empty[Int])(fid) == Nil)
  }

  test("OptionF.map behaves like the collection's map") {
    assert(OptionF.map(Some(2))(fid) == Some(3.0))
    assert(OptionF.map(Option.empty[Int])(fid) == None)
  }
