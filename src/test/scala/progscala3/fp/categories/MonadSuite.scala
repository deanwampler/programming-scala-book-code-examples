// src/test/scala/progscala3/fp/categories/MonadSuite.scala
package progscala3.fp.categories

import munit.*
import scala.language.higherKinds                         // <1>

class MonadExampleSuite extends FunSuite:

  val seqf: Int => Seq[Int] = i => 1 to i
  val optf: Int => Option[Int] = i => Option(i + 1)

  test("SeqM.flatMap behaves like the collection's flatMap") {
    assert(SeqM.flatMap(Seq(1,2,3))(seqf) == Seq(1, 1, 2, 1, 2, 3))
    assert(SeqM.flatMap(Seq.empty[Int])(seqf) == Nil)
  }

  test("OptionM.flatMap behaves like the collection's flatMap") {
    assert(OptionM.flatMap(Some(2))(optf) == Some(3))
    assert(OptionM.flatMap(Option.empty[Int])(optf) == None)
  }
