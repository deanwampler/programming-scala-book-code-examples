// src/script/scala/progscala3/fp/categories/FunctorExample.scala
import progscala3.fp.categories._

val fid: Int => Double    = i => 1.5 * i

assert(SeqF.map(Seq(1,2,3,4))(fid)   == Seq(1.5, 3.0, 4.5, 6.0))
assert(SeqF.map(Seq.empty[Int])(fid) == Nil)

assert(OptionF.map(Some(2))(fid) == Some(3.0))
assert(OptionF.map(Option.empty[Int])(fid) == None)
