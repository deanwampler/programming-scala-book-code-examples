// src/main/scala/FP/datastructs/foldleft-map.sc

List(1, 2, 3, 4, 5, 6).foldLeft(List[String]()) {
  (list, x) => ("<" + x + ">") :: list
}.reverse
