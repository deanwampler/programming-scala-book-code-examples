// src/main/scala/progscala2/fp/datastructs/list.sc

val list = List(1, 2, 3, 4, 5)
(list scan 10) (_ + _)