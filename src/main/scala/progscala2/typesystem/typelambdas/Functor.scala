// src/main/scala/progscala2/typesystem/typelambdas/Functor.scala
package progscala2.typesystem.typelambdas
import scala.language.higherKinds

trait Functor[A,+M[_]] {                                             // <1>
  def map2[B](f: A => B): M[B]
}
object Functor {                                                     // <2>
  implicit class SeqFunctor[A](seq: Seq[A]) extends Functor[A,Seq] {
    def map2[B](f: A => B): Seq[B] = seq map f
  }
  implicit class OptionFunctor[A](opt: Option[A]) extends Functor[A,Option] {
    def map2[B](f: A => B): Option[B] = opt map f
  }

  implicit class MapFunctor[K,V1](mapKV1: Map[K,V1])                 // <3>
    extends Functor[V1,({type λ[α] = Map[K,α]})#λ] {                 // <4>
      def map2[V2](f: V1 => V2): Map[K,V2] = mapKV1 map {
        case (k,v) => (k,f(v))
      }
    }
}
