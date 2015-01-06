// src/main/scala/progscala2/fp/categories/Functor.scala
package progscala2.fp.categories
import scala.language.higherKinds

trait Functor[F[_]] {                                                // <1>
  def map[A, B](fa: F[A])(f: A => B): F[B]                           // <2>
}

object SeqF extends Functor[Seq] {                                   // <3>
  def map[A, B](seq: Seq[A])(f: A => B): Seq[B] = seq map f
}

object OptionF extends Functor[Option] {
  def map[A, B](opt: Option[A])(f: A => B): Option[B] = opt map f
}

object FunctionF {                                                   // <4>
  def map[A,A2,B](func: A => A2)(f: A2 => B): A => B = {             // <5>
    val functor = new Functor[({type λ[β] = A => β})#λ] {            // <6>
      def map[A3,B2](func: A => A3)(f: A3 => B2): A => B2 = (a: A) => f(func(a))
    }
    functor.map(func)(f)                                             // <7>
  }
}
