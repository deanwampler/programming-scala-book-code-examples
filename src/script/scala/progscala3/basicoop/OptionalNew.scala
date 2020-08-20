// src/script/scala/progscala3/basicoop/MethodOverloadsMultiArgLists.scala

object Stringizer:
  def makeString(map: Map[?,?])(prefixSpaceCount: Int): String =
    map.map{ (k,v) => (" "*prefixSpaceCount) + s"$k -> $v"}.mkString("\n")

  def makeString(map: Map[?,?])(prefix: String): String =
    map.map{ (k,v) => s"$prefix$k -> $v"}.mkString("\n")

val map = Map("one" -> 1, "two" -> 2, "three" -> 3)

Stringizer.makeString(map)(2)
Stringizer.makeString(map)("~~")
