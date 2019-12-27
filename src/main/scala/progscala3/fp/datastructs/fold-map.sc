// src/main/scala/progscala3/fp/datastructs/fold-map.sc

(List(1, 2, 3, 4, 5, 6) foldRight List.empty[String]) {
  (x, list) => ("[" + x + "]") :: list
}
