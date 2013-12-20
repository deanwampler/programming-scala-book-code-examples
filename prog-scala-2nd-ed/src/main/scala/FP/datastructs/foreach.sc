// code-examples/FP/datastructs/foreach-script.scala

List(1, 2, 3, 4, 5) foreach { i => println("Int: " + i) }

val stateCapitals = Map(
  "Alabama" -> "Montgomery",
  "Alaska"  -> "Juneau",
  "Wyoming" -> "Cheyenne")

stateCapitals foreach { kv => println(kv._1 + ": " + kv._2) }
