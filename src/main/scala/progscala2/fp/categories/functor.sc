// src/main/scala/progscala2/fp/categories/Functor.sc
import progscala2.fp.categories._
import scala.language.higherKinds

val fii: Int => Int       = i => i * 2
val fid: Int => Double    = i => 2.1 * i
val fds: Double => String = d => d.toString

SeqF.map(List(1,2,3,4))(fii)                // Seq[Int]: List(2, 4, 6, 8)
SeqF.map(List.empty[Int])(fii)              // Seq[Int]: List()

OptionF.map(Some(2))(fii)                   // Option[Int]: Some(4)
OptionF.map(Option.empty[Int])(fii)         // Option[Int]: None

val fa = FunctionF.map(fid)(fds)                                     // <1>
fa(2)                                       // String: 4.2

// val fb = FunctionF.map(fid)(fds)                                     <2>
val fb = FunctionF.map[Int,Double,String](fid)(fds)
fb(2)

val fc = fds compose fid                                             // <3>
fc(2)                                       // String: 4.2
