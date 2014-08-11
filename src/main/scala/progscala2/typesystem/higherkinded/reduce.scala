// src/main/scala/progscala2/typesystem/higherkinded/Reduce.scala
package progscala2.typesystem.higherkinded
import scala.language.higherKinds                                    // <1>

trait Reduce[T, -M[T]] {                                             // <2>
  def reduce(m: M[T])(f: (T, T) => T): T
}

object Reduce {                                                      // <3>
  implicit def seqReduce[T] = new Reduce[T, Seq] {
    def reduce(seq: Seq[T])(f: (T, T) => T): T = seq reduce f
  }

  implicit def optionReduce[T] = new Reduce[T, Option] {
    def reduce(opt: Option[T])(f: (T, T) => T): T = opt reduce f
  }
}
