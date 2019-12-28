// src/main/scala/progscala3/fp/datastructs/map.sc

val stateCapitals = Map(
  "Alabama" -> "Montgomery",
  "Alaska"  -> "Juneau",
  "Wyoming" -> "Cheyenne")

val lengths = stateCapitals map { 
  kv => (kv._1, kv._2.length) 
}
assert(lengths == Map(
  "Alabama" -> 10,
  "Alaska"  -> 6,
  "Wyoming" -> 8))

val caps = stateCapitals map { 
  case (k, v) => (k, v.toUpperCase)
}
assert(caps ==  Map(
  "Alabama" -> "MONTGOMERY",
  "Alaska"  -> "JUNEAU",
  "Wyoming" -> "CHEYENNE"))

val stateCapitals2 = stateCapitals + ("Virginia" -> "Richmond")
assert(stateCapitals2 == Map(
  "Alabama" -> "Montgomery",
  "Alaska"  -> "Juneau",
  "Wyoming" -> "Cheyenne",
  "Virginia" -> "Richmond"))

val stateCapitals3 = stateCapitals2 ++ Seq(                  // <1>
  "New York" -> "Albany", "Illinois" -> "Springfield")
assert(stateCapitals3 == Map(
  "Alabama" -> "Montgomery",
  "Alaska"  -> "Juneau",
  "Wyoming" -> "Cheyenne",
  "Virginia" -> "Richmond",
  "New York" -> "Albany", 
  "Illinois" -> "Springfield"))

