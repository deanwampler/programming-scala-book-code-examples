// src/main/scala/Rounding/match-list.sc

val nonEmptyList = List(1, 2, 3, 4, 5)
val emptyList    = List()

def processList[T](l: List[T]): Unit = l match {
  case head :: tail => 
    printf("%s :: ", head)
    processList(tail)
  case Nil => println("Nil")
}

for (l <- List(nonEmptyList, emptyList)) {
  print("List: ")
  processList(l)
}
