// src/main/scala/progscala3/fp/datastructs/foreach.sc

List(1, 2, 3, 4, 5).foreach { i => println(s"Int: $i") }

val stateCapitals = Map(
  "Alabama" -> "Montgomery",
  "Alaska"  -> "Juneau",
  "Wyoming" -> "Cheyenne")

// Without pattern matching:
stateCapitals.foreach { kv => println(s"${kv._1}: ${kv._2}") }

// With pattern matching:
stateCapitals.foreach { case (k, v) => println(s"$k: $v") }

// With pattern matching and index zipping.
// (Note that maps don't guarantee order!)
stateCapitals.zipWithIndex.foreach { 
  case ((k, v), i) => println(s"($i) $k: $v") 
}
