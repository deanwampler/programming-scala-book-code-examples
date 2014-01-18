// src/main/scala/PatternMatching/match-tuple.sc

val langs = List(
  ("Scala",   "Martin", "Odersky"),
  ("Clojure", "Rich",   "Hickey"),
  ("Lisp",    "John",   "McCarthy"))

for (tuple <- langs) {
  tuple match {
    case ("Scala", _, _) => println("Found Scala")
    case (lang, first, last) => println(s"Other language: $lang ($first, $last)")
  }
}
