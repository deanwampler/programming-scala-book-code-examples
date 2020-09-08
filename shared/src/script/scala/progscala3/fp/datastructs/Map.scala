// src/script/scala/progscala3/fp/datastructs/Map.scala

val stateCapitals = Map(
  "Alabama" -> "Montgomery",
  "Alaska"  -> "Juneau",
  "Wyoming" -> "Cheyenne")

val lengths = stateCapitals map(kv => (kv._1, kv._2.length))
assert(lengths == Map(
  "Alabama" -> 10,
  "Alaska"  -> 6,
  "Wyoming" -> 8))

val caps = stateCapitals map { case (k, v) => (k, v.toUpperCase) }
assert(caps ==  Map(
  "Alabama" -> "MONTGOMERY",
  "Alaska"  -> "JUNEAU",
  "Wyoming" -> "CHEYENNE"))

val stateCapitals2a = stateCapitals + ("Virginia" -> "Richmond")
assert(stateCapitals2a == Map(
  "Alabama" -> "Montgomery",
  "Alaska"  -> "Juneau",
  "Wyoming" -> "Cheyenne",
  "Virginia" -> "Richmond"))

val stateCapitals2b = stateCapitals + ("Alabama" -> "MONTGOMERY")
assert(stateCapitals2b == Map(
  "Alabama" -> "MONTGOMERY",
  "Alaska"  -> "Juneau",
  "Wyoming" -> "Cheyenne"))

val stateCapitals2 = stateCapitals ++ Seq(
  "Virginia" -> "Richmond", "Illinois" -> "Springfield")
assert(stateCapitals2 == Map(
  "Alabama" -> "Montgomery",
  "Alaska"  -> "Juneau",
  "Wyoming" -> "Cheyenne",
  "Virginia" -> "Richmond",
  "Illinois" -> "Springfield"))
