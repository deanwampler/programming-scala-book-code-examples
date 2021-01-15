// tag::first[]
// src/script/scala/progscala3/patternmatching/MatchTuple.scala

val langs = Seq(
  ("Scala",   "Martin", "Odersky"),
  ("Clojure", "Rich",   "Hickey"),
  ("Lisp",    "John",   "McCarthy"))

val results = langs.map {
  case ("Scala", _, _) => "Scala"                               // <1>
  case (lang, first, last) => s"$lang, creator $first $last"    // <2>
}
// end::first[]

assert(results == Seq(
  "Scala",
  "Clojure, creator Rich Hickey",
  "Lisp, creator John McCarthy"))

langs.map {
  case "Scala" *: first *: last *: EmptyTuple =>
    s"Scala -> $first -> $last"
  case lang *: rest => s"$lang -> $rest"
}

val l2 = EmptyTuple +: ("Indo-European" *: EmptyTuple) +: langs
l2.map {
  case "Scala" *: first *: last *: EmptyTuple =>
    s"Scala -> $first -> $last"
  case lang *: rest => s"$lang -> $rest"
  case EmptyTuple => EmptyTuple.toString
}
