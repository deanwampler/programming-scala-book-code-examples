// src/script/scala/progscala3/patternmatching/UnapplySingleValue.scala

import java.util.HashMap as JHashMap

case class JHashMapWrapper[K,V](jmap: JHashMap[K,V])
object JHashMapWrapper:
  def unapply[K,V](map: Map[K,V]): JHashMapWrapper[K,V] =
    val jmap = new JHashMap[K,V]()
    for (k,v) <- map do jmap.put(k, v)
    new JHashMapWrapper(jmap)

val map = Map("one" -> 1, "two" -> 2)
map match
  case JHashMapWrapper(jmap) => jmap
