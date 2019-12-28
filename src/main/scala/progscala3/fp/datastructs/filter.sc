// src/main/scala/progscala3/fp/datastructs/filter.sc

val stateCapitals = Map(
  "Alabama" -> "Montgomery",
  "Alaska"  -> "Juneau",
  "Wyoming" -> "Cheyenne")
  
val mapA = stateCapitals filter { kv => kv._1 startsWith "A" }

assert(mapA == Map(
  "Alabama" -> "Montgomery",
  "Alaska"  -> "Juneau"))
