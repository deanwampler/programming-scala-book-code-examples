// src/main/scala/progscala3/patternmatching/match-list.sc

val nonEmptyList = List(1, 2, 3, 4, 5)
val emptyList    = Nil

def listToString[T](list: List[T]): String = list match {
  case head :: tail => s"($head :: ${listToString(tail)})"           // <1>
  case Nil => "(Nil)"
}

val results = List(nonEmptyList, emptyList) map (l => listToString(l))
assert(results == Seq(
  "(1 :: (2 :: (3 :: (4 :: (5 :: (Nil))))))", 
  "(Nil)"))
