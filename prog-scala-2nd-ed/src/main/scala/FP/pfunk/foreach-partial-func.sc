// code-examples/FP/pfunk/foreach-partial-func-script.scala

val stateCapitals = Map(
  "Alabama" -> "Montgomery",
  "Alaska"  -> "Juneau",
  "Wyoming" -> "Cheyenne")
  
stateCapitals foreach { case (key, value) => println (key + ": " + value) }
