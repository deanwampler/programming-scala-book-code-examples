// src/main/scala/progscala2/fp/datastructs/foreach.sc

List(1, 2, 3, 4, 5) foreach { i => println("Int: " + i) }

val stateCapitals = Map(
  "Alabama" -> "Montgomery",
  "Alaska"  -> "Juneau",
  "Wyoming" -> "Cheyenne")

//stateCapitals foreach { kv => println(kv._1 + ": " + kv._2) }
stateCapitals foreach { case (k, v) => println(k + ": " + v) }
