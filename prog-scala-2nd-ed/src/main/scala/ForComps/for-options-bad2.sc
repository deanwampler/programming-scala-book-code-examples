// src/main/scala/ForComps/for-options-bad.sc

val successfulCounts = List(Some(5), Some(21), Some(8))
val partiallySuccessfulCounts = List(Some(5), None, Some(8))

def sumCountsBad(counts: List[Option[Int]]): Option[Int] = 
  (counts foldLeft Option(0)) {
    (countOption, count) => 
      if (countOption == None || count == None) None
      else Some(countOption.get + count.get)
  }

sumCountsBad(successfulCounts)
sumCountsBad(partiallySuccessfulCounts)
