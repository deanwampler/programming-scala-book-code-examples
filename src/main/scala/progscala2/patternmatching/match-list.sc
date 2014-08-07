// src/main/scala/progscala2/patternmatching/match-list.sc

val nonEmptyList = List(1, 2, 3, 4, 5)
val emptyList    = Nil

def listToString[T](list: List[T]): String = list match {
  case head :: tail => s"($head :: ${listToString(tail)})"           // <1>
  case Nil => "(Nil)"
}

for (l <- List(nonEmptyList, emptyList)) { println(listToString(l)) }
