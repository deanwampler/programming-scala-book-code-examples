// src/main/scala/FP/datastructs/flatmap.sc

val list = List("now", "is", "", "the", "time")

list flatMap (s => s.toList)

val list2 = List("now", "is", "", "the", "time") map (s => s.toList)
list2.flatten