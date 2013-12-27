// src/main/scala/FP/datastructs/map.sc

val stateCapitals = Map(
  "Alabama" -> "Montgomery",
  "Alaska"  -> "Juneau",
  "Wyoming" -> "Cheyenne")

val lengths = stateCapitals map { kv => (kv._1, kv._2.length) }
println(lengths)
