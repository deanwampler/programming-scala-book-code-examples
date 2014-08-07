// src/main/scala/progscala2/fp/pfunk/foreach-partial-func.sc

val stateCapitals = Map(
  "Alabama" -> "Montgomery",
  "Alaska"  -> "Juneau",
  "Wyoming" -> "Cheyenne")
  
stateCapitals foreach { case (key, value) => println (key + ": " + value) }
