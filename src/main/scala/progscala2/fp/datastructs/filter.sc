// src/main/scala/progscala2/fp/datastructs/filter.sc

val stateCapitals = Map(
  "Alabama" -> "Montgomery",
  "Alaska"  -> "Juneau",
  "Wyoming" -> "Cheyenne")
  
val map2 = stateCapitals filter { kv => kv._1 startsWith "A" }

println( map2 )
