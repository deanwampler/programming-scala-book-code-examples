// src/script/scala/progscala3/implicits/CollectiveExtensionMethods.scala

import progscala3.implicits.GenericExtensionMethods._
import progscala3.implicits.CollectiveExtensionMethods._

val map1 = Map("one" -> 1, "two" -> 2)
val map2 = Map("two" -> 2, "three" -> 3)
assert(map1.sorted == Vector("one" -> 1, "two" -> 2))
assert(map2.sorted == Vector("three" -> 3, "two" -> 2))

given intMonoid as Monoid[Int]:
  def add(i1: Int, i2: Int): Int = i1+i2
  def zero: Int = 0

assert(map1.merge(map2) == Map("one" -> 1, "two" -> 4, "three" -> 3))
