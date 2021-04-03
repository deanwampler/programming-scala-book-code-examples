// src/script/scala/progscala3/fp/categories/MapMerge.scala

import progscala3.fp.categories.MapMergeMonoid                  // <1>
import progscala3.contexts.typeclass.given

val map1i = Map("one" -> 1, "two" -> 2)                         // <2>
val map2i = Map("two" -> 2, "three" -> 3)
val map1s = map1i.map{ (k,v) => (k, v.toString) }               // <3>
val map2s = map2i.map{ (k,v) => (k, v.toString) }

map1i.combine(map2i)
map1s.combine(map2s)
map1s <+> map2s

assert(map1i.combine(map2i).equals(Map("one" -> 1, "two" -> 4, "three" -> 3)))
assert(map1s.combine(map2s).equals(Map("one" -> "1", "two" -> "22", "three" -> "3")))
assert((map1s <+> map2s).equals(Map("one" -> "1", "two" -> "22", "three" -> "3")))
