// src/script/scala/progscala3/collections/CollectionsEquality.scala

import collection.immutable.{TreeSet, VectorMap}

List(1,2,3) == Vector(1,2,3)
List(1,2,3) == Vector(3,2,1)

Set(1,2,3) == Set(3,2,1)
Set(1,2,3) == TreeSet(3,2,1)
TreeSet(1,2,3) == Set(3,2,1)
TreeSet(1,2,3) == TreeSet(3,2,1)

Map("one" -> 1, "two" -> 2) == Map("two" -> 2, "one" -> 1)
Map("one" -> 1, "two" -> 2) == VectorMap("two" -> 2, "one" -> 1)
VectorMap("one" -> 1, "two" -> 2) == Map("two" -> 2, "one" -> 1)
VectorMap("one" -> 1, "two" -> 2) == VectorMap("two" -> 2, "one" -> 1)

