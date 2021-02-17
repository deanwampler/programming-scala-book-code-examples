// src/test/scala/progscala3/fp/datastructs/ForeachSuite.scala
package progscala3.fp.datastructs

import munit.*

class ForeachSuite extends FunSuite:

  val stateCapitals = Map(
    "Alabama" -> "Montgomery",
    "Alaska"  -> "Juneau",
    "Wyoming" -> "Cheyenne")
  val expected = "Alabama: Montgomery, Alaska: Juneau, Wyoming: Cheyenne, "

  test("Foreach does pure side effects") {
    var str = ""
    stateCapitals.foreach(kv => str += s"${kv._1}: ${kv._2}, ")
    assert(str == expected)
  }

  test("Foreach can take a partial function") {
    var str = ""
    stateCapitals.foreach { case (k, v) => str += s"${k}: ${v}, " }
    assert(str == expected)
  }

  test("Use zipWithIndex to get an index for each item") {
    var str = ""
    stateCapitals.zipWithIndex.foreach {
      case ((k, v), i) => str += s"($i) $k: $v, "
    }
    assert(str ==
      "(0) Alabama: Montgomery, (1) Alaska: Juneau, (2) Wyoming: Cheyenne, ")

  }
