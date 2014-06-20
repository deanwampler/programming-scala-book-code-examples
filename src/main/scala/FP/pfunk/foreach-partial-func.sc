// src/main/scala/FP/pfunk/foreach-partial-func.sc

val stateCapitals = Map(
  "Alabama" -> "Montgomery",
  "Alaska"  -> "Juneau",
  "Wyoming" -> "Cheyenne")
  
stateCapitals foreach { case (key, value) => println (key + ": " + value) }
