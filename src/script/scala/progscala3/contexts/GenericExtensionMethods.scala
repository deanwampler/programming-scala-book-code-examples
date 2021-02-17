// src/script/scala/progscala3/contexts/GenericExtensionMethods.scala

import progscala3.contexts.GenericExtensionMethods.*

val items1 = Seq(5, 1, 3, 1, 2, 2, 4, 3, 1, 4, 5)
val items2 = items1.map(_.toString)
assert(items1.sortedUnique  == Vector(1, 2, 3, 4, 5))
assert(items2.sortedUnique  == Vector("1", "2", "3", "4", "5"))
