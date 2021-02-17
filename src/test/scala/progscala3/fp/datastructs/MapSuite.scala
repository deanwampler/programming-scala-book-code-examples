// src/test/scala/progscala3/fp/datastructs/MapSuite.scala
package progscala3.fp.datastructs

import munit.*

class MapSuite extends FunSuite:

  val stateCapitals = Map(
    "Alabama" -> "Montgomery",
    "Alaska"  -> "Juneau",
    "Wyoming" -> "Cheyenne")

  test("Map transforms a collection one to one") {
    val lengths = stateCapitals map {
      kv => (kv._1, kv._2.length)
    }
    assert(lengths == Map(
      "Alabama" -> 10,
      "Alaska"  -> 6,
      "Wyoming" -> 8))
  }

  test("Map can take a partial function") {
    val caps = stateCapitals map {
      case (k, v) => (k, v.toUpperCase)
    }
    assert(caps == Map(
      "Alabama" -> "MONTGOMERY",
      "Alaska"  -> "JUNEAU",
      "Wyoming" -> "CHEYENNE"))
  }

  test("Add an element with +") {
    val stateCapitals2 = stateCapitals + ("Virginia" -> "Richmond")
    assert(stateCapitals2 == Map(
      "Alabama" -> "Montgomery",
      "Alaska"  -> "Juneau",
      "Wyoming" -> "Cheyenne",
      "Virginia" -> "Richmond"))
  }

  test("Join maps with ++") {
    val stateCapitals2 = stateCapitals ++ Seq(
      "Virginia" -> "Richmond", "Illinois" -> "Springfield")
    assert(stateCapitals2 == Map(
      "Alabama" -> "Montgomery",
      "Alaska"  -> "Juneau",
      "Wyoming" -> "Cheyenne",
      "Virginia" -> "Richmond",
      "Illinois" -> "Springfield"))
  }
