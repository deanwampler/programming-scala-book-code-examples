// src/script/scala/progscala3/collections/GroupByWC.scala

val whitespace = "\\W+"
val lines = io.Source.fromFile("README.md").getLines
val words = lines.flatMap(_.toLowerCase.replaceAll(whitespace, " ").
  split(whitespace)).filter(_.length > 0).toVector
words.take(10)

val grouped = words.groupBy(identity)
val wordCounts = grouped.view.mapValues(_.size).toSeq.sortBy(-_._2)
