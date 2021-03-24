// src/main/scala/progscala3/fp/categories/MapMerge.scala
package progscala3.fp.categories
import progscala3.contexts.typeclass.Monoid

given MapMergeMonoid[K, V : Monoid]: Monoid[Map[K, V]] with     // <1>
  def unit: Map[K, V] = Map.empty
  extension (map1: Map[K, V]) def combine(map2: Map[K, V]): Map[K, V] =
    val kmon = summon[Monoid[V]]
    (map1.keySet union map2.keySet).map { k =>
      val v1 = map1.getOrElse(k, kmon.unit)
      val v2 = map2.getOrElse(k, kmon.unit)
      k -> (v1 combine v2)
    }.toMap
