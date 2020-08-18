// src/script/scala/progscala3/forcomps/ForOptionsBad2.scala

/** Another "bad" example, when easier idioms exist. */

def sumCountsBad(counts: Seq[Option[Int]]): Option[Int] =
  counts.foldLeft(Option(0)) {
    (countOption, count) =>
      if countOption == None || count == None then None
      else Some(countOption.get + count.get)
  }

val successfulCounts = Seq(Some(5), Some(21), Some(8))
val partiallySuccessfulCounts = Seq(Some(5), None, Some(8))

sumCountsBad(successfulCounts)
sumCountsBad(partiallySuccessfulCounts)
