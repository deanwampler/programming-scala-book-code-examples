// src/script/scala/progscala3/patternmatching/MatchForFiltering.scala

val elems = Seq((1, 2), "hello", (3, 4), 1, 2.2, (5, 6))   // <1>

val what1 = for (case (x, y) <- elems) yield (y, x)        // <2>
val what2 = for case (x, y) <- elems yield (y, x)

val nope = for (x, y) <- elems yield (y, x)
