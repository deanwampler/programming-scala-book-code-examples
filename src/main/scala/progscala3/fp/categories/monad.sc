// src/main/scala/progscala3/fp/categories/Monad.sc
import progscala3.fp.categories._
import scala.language.higherKinds

val seqf: Int => Seq[Int] = i => 1 to i
val optf: Int => Option[Int] = i => Option(i + 1)

assert(SeqM.flatMap(List(1,2,3))(seqf)   == List(1, 1, 2, 1, 2, 3))
assert(SeqM.flatMap(List.empty[Int])(seqf) == Nil)

assert(OptionM.flatMap(Some(2))(optf) == Some(3))
assert(OptionM.flatMap(Option.empty[Int])(optf) == None)
