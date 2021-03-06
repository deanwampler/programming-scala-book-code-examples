// src/main/scala/progscala3/typesystem/typelambdas/Functor.scala
package progscala3.typesystem.typelambdas

trait Functor[A, M[_]]:
  extension (m: M[A]) def map2[B](f: A => B): M[B]

object Functor:
  given [A]: Functor[A, Seq] with
    extension (seq: Seq[A]) def map2[B](f: A => B): Seq[B] = seq map f

  type MapK = [K] =>> [V] =>> Map[K,V]                          // <1>

  given [K, V1]: Functor[V1, MapK[K]] with                      // <2>
    extension (map: MapK[K][V1])
      def map2[V2](f: V1 => V2): MapK[K][V2] = map.view.mapValues(f).toMap

