// src/script/scala/progscala3/patternmatching/MatchTuple.scala

val langs = Seq(
  ("Scala",   "Martin", "Odersky"),
  ("Clojure", "Rich",   "Hickey"),
  ("Lisp",    "John",   "McCarthy"))

val results = langs map {
  case ("Scala", _, _) => "Scala"                               // <1>
  case (lang, first, last) => s"$lang, creator $first $last"    // <2>
}
assert(results == Seq(
  "Scala",
  "Clojure, creator Rich Hickey",
  "Lisp, creator John McCarthy"))
