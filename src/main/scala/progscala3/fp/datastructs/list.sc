// src/main/scala/progscala3/fp/datastructs/list.sc

val list1 = List("Programming", "Scala")                             // <1>
val list2 = "People" :: "should" :: "read" :: list1                  // <2>
assert(list2 == 
  List("People", "should", "read", "Programming", "Scala"))

val list3 = "Programming" :: "Scala" :: Nil                          // <3>
val list4 = "People" :: "should" :: "read" :: Nil
val list5 = list4 ++ list3                                           // <4>
assert(list5 == 
  List("People", "should", "read", "Programming", "Scala"))
