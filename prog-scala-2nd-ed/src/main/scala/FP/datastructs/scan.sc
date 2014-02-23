// src/main/scala/FP/datastructs/list.sc

val list = List(1, 2, 3, 4, 5)
(list scan 10) (_ + _)