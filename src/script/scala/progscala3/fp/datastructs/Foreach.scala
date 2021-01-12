// src/script/scala/progscala3/fp/datastructs/Foreach.scala
val stateCapitals = Map(
  "Alabama" -> "Montgomery",
  "Alaska"  -> "Juneau",
  "Wyoming" -> "Cheyenne")
val expected = "Alabama: Montgomery, Alaska: Juneau, Wyoming: Cheyenne, "

var str1 = ""
stateCapitals.foreach { kv => str1 += s"${kv._1}: ${kv._2}, " }
assert(str1 == expected)

var str2 = ""
stateCapitals.foreach { case (k, v) => str2 += s"${k}: ${v}, " }
assert(str2 == expected)

var str3 = ""
stateCapitals.zipWithIndex.foreach {
  case ((k, v), i) => str3 += s"($i) $k: $v, "
}
assert(str3 ==
  "(0) Alabama: Montgomery, (1) Alaska: Juneau, (2) Wyoming: Cheyenne, ")
