// src/main/scala/progscala3/fp/categories/Monad.sc
// Use the following if you don't use -language:higherKinds
// import scala.language.higherKinds                                 // <1>
import progscala3.fp.categories._

val seqf: Int => Seq[Int] = i => 1 to i
val optf: Int => Option[Int] = i => Option(i + 1)

assert(SeqM.flatMap(Seq(1,2,3))(seqf)   == Seq(1, 1, 2, 1, 2, 3))
assert(SeqM.flatMap(Seq.empty[Int])(seqf) == Nil)

assert(OptionM.flatMap(Some(2))(optf) == Some(3))
assert(OptionM.flatMap(Option.empty[Int])(optf) == None)
