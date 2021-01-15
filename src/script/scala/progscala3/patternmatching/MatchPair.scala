// src/script/scala/progscala3/patternmatching/MatchPair.scala

val langs2 = Seq("Scala" -> "Odersky", "Clojure" -> "Hickey")

val results = langs2.map {
  case "Scala" -> _ => "Scala"                           // <1>
  case lang -> last => s"$lang: $last"                   // <2>
}
assert(results == Seq("Scala", "Clojure: Hickey"))
