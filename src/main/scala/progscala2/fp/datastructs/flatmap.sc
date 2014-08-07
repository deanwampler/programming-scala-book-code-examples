// src/main/scala/progscala2/fp/datastructs/flatmap.sc

val list = List("now", "is", "", "the", "time")

list flatMap (s => s.toList)

// Like flatMap, but flatMap eliminates the intermediate step!
val list2 = List("now", "is", "", "the", "time") map (s => s.toList)
list2.flatten