// src/script/scala/progscala3/patternmatching/UnapplySingleValue2.scala

import java.util.{HashMap as JHashMap, HashSet as JHashSet}

case class JHashMapWrapper[K,V](jmap: JHashMap[K,V])
object JHashMapWrapper:
  def unapply[K,V](map: Map[K,V]): JHashMapWrapper[K,V] =
    val jmap = new JHashMap[K,V]()
    for (k,v) <- map do jmap.put(k, v)
    new JHashMapWrapper(jmap)

case class JHashSetWrapper[K](jset: JHashSet[K])
object JHashSetWrapper:
  def unapply[K](set: Set[K]): JHashSetWrapper[K] =
    val jset = new JHashSet[K]()
    for k <- set do jset.add(k)
    JHashSetWrapper(jset)

val map = Map("one" -> 1, "two" -> 2)
val set = map.keySet

// Works:
map match
  case JHashMapWrapper(jmap) => jmap
set match
  case JHashSetWrapper(jset) => jset

// This fails to compile because there are _two_ possible returned values.
for x <- Seq(map, set) yield x match
  case JHashMapWrapper(jmap) => jmap
  case JHashSetWrapper(jset) => jset
