// src/script/scala/progscala3/fp/categories/FunctorExample.scala
import progscala3.fp.categories._

val fii: Int => Int       = i => i * 2
val fid: Int => Double    = i => 2.1 * i
val fds: Double => String = d => d.toString

assert(SeqF.map(Seq(1,2,3,4))(fii)   == Seq(2, 4, 6, 8))
assert(SeqF.map(Seq.empty[Int])(fii) == Nil)

assert(OptionF.map(Some(2))(fii) == Some(4))
assert(OptionF.map(Option.empty[Int])(fii) == None)

val fa = FunctionF.map(fid)(fds)                             // <1>
assert(fa(2) == "4.2")

val fb = FunctionF.map[Int,Double,String](fid)(fds)          // <2>
assert(fb(2) == "4.2")

val fc = fds compose fid                                     // <3>
assert(fc(2) == "4.2")
