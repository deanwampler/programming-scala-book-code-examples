// src/main/scala/progscala3/typesystem/typelambdas/Functor.scala
package progscala3.typesystem.typelambdas

trait Functor[M[_]]:
  extension [A] (m: M[A]) def map2[B](f: A => B): M[B]

object Functor:
  given Functor[Seq] with
    extension [A] (seq: Seq[A]) def map2[B](f: A => B): Seq[B] = seq map f

  type MapKV = [K] =>> [V] =>> Map[K,V]                         // <1>

  given [K]: Functor[MapKV[K]] with                             // <2>
    extension [V1] (map: MapKV[K][V1])
      def map2[V2](f: V1 => V2): MapKV[K][V2] = map.view.mapValues(f).toMap

