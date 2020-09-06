// src/main/scala/progscala3/fp/categories/MapMerge.scala

package progscala3.fp.categories

object MapMerge:
  trait Monoid[A]:                                                   // <1>
    def add(a1: A, a2: A): A
    def zero: A

  extension [K,V](map: Map[K,V]):                                    // <2>
    def merge(other: Map[K,V])(using mon: Monoid[V]): Map[K,V] =
      (map.keySet union other.keySet).map { k =>
        val v1 = map.getOrElse(k, mon.zero)
        val v2 = other.getOrElse(k, mon.zero)
        k -> mon.add(v1, v2)
      }.toMap
