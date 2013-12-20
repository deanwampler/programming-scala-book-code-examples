// code-examples/FP/datastructs/filter-script.scala

val stateCapitals = Map(
  "Alabama" -> "Montgomery",
  "Alaska"  -> "Juneau",
  "Wyoming" -> "Cheyenne")
  
val map2 = stateCapitals filter { kv => kv._1 startsWith "A" }

println( map2 )
