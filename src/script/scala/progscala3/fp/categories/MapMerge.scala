// src/script/scala/progscala3/fp/categories/MapMerge.scala

import progscala3.fp.categories.MapMerge._

given Monoid[Int]:                                              // <1>
  def add(i1: Int, i2: Int): Int = i1+i2
  def unit: Int = 0
given Monoid[String]:
  def add(s1: String, s2: String): String = s1+s2
  def unit: String = ""

val map1i = Map("one" -> 1, "two" -> 2)                         // <2>
val map2i = Map("two" -> 2, "three" -> 3)
val map1s = map1i.map{ (k,v) => (k, v.toString) }               // <3>
val map2s = map2i.map{ (k,v) => (k, v.toString) }

assert(map1i.merge(map2i) == Map("one" -> 1, "two" -> 4, "three" -> 3))
assert(map1s.merge(map2s) == Map("one" -> "1", "two" -> "22", "three" -> "3"))
