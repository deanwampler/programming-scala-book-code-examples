// src/script/scala/progscala3/patternmatching/MatchInterpolatedString.scala

val catalog = Seq(
  "Book: title=Programming Scala Third Edition, author=Dean Wampler",
  "Magazine: title=The New Yorker, issue=January 2021",
  "Unknown: text=Who put this here??"
)

val results = catalog.map {
  case s"""Book: title=$t, author=$a""" => ("Book" -> (t -> a))
  case s"""Magazine: title=$t, issue=$d""" => ("Magazine" -> (t -> d))
  case item => ("Unrecognized", item)
}
assert(results == Seq(
  ("Book", ("Programming Scala Third Edition", "Dean Wampler")),
  ("Magazine", ("The New Yorker", "January 2021")),
  ("Unrecognized", "Unknown: text=Who put this here??")))
