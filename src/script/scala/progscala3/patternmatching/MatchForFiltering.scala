// src/script/scala/progscala3/patternmatching/MatchForFiltering.scala

val elems = Seq((1, 2), "hello", (3, 4), 1, 2.2, (5, 6))

val what1 = for (case (x, y) <- elems) yield (y, x)        // <1>
val what2 = for  case (x, y) <- elems  yield (y, x)

val nope = for (x, y) <- elems yield (y, x)

val seq = Seq(None, Some(1), None, Some(2.2), None, None, Some("three"))
for case Some(x) <- seq yield x
