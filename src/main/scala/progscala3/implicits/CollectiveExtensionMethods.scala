// src/main/scala/progscala3/implicits/CollectiveExtensionMethods.scala

package progscala3.implicits


object CollectiveExtensionMethods:
  import GenericExtensionMethods._                                   // <1>

  trait Monoid[A]:                                                   // <2>
    def add(a1: A, a2: A): A
    def zero: A

  extension [K,V](map: Map[K,V]):                                    // <3>

    def sorted(using Ordering[K]): Seq[(K,V)] =                      // <4>
      map.keySet.toSeq.sortedUnique.map(key => (key, map(key)))

    def merge(other: Map[K,V])(using mon: Monoid[V]): Map[K,V] =     // <5>
      (map.keySet union other.keySet).map { k =>
        val v1 = map.getOrElse(k, mon.zero)
        val v2 = other.getOrElse(k, mon.zero)
        k -> mon.add(v1, v2)
      }.toMap
