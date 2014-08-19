// src/main/scala/progscala2/fp/categories/Monad.sc
import progscala2.fp.categories._
import scala.language.higherKinds

val seqf: Int => Seq[Int] = i => 1 to i
val optf: Int => Option[Int] = i => Option(i + 1)

SeqM.flatMap(List(1,2,3))(seqf)             // Seq[Int]: List(1,1,2,1,2,3)
SeqM.flatMap(List.empty[Int])(seqf)         // Seq[Int]: List()

OptionM.flatMap(Some(2))(optf)              // Option[Int]: Some(3)
OptionM.flatMap(Option.empty[Int])(optf)    // Option[Int]: None
