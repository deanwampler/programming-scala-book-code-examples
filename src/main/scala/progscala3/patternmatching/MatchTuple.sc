// src/main/scala/progscala3/patternmatching/match-tuple.sc

val langs = Seq(
  ("Scala",   "Martin", "Odersky"),
  ("Clojure", "Rich",   "Hickey"),
  ("Lisp",    "John",   "McCarthy"))

val results = langs map {
  case ("Scala", _, _) => "Found Scala"                      // <1>
  case (lang, first, last) =>                                // <2>
    s"Found other language: $lang ($first $last)"
}
assert(results == Seq(
  "Found Scala", 
  "Found other language: Clojure (Rich Hickey)",
  "Found other language: Lisp (John McCarthy)"))
