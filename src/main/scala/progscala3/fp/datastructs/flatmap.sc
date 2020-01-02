// src/main/scala/progscala3/fp/datastructs/flatmap.sc

val list = List("now", "is", "", "the", "time")

val l1 = list flatMap (s => s.toList)
assert(l1 == 
  List[Char]('n', 'o', 'w', 'i', 's', 't', 'h', 'e', 't', 'i', 'm', 'e'))

// Like flatMap, but flatMap eliminates the need for two steps!
val l2 = list map (s => s.toList)
assert(l2 == 
  List[List[Char]](List('n', 'o', 'w'), List('i', 's'), List(), 
    List('t', 'h', 'e'), List('t', 'i', 'm', 'e')))
assert(l2.flatten == 
  List[Char]('n', 'o', 'w', 'i', 's', 't', 'h', 'e', 't', 'i', 'm', 'e'))
