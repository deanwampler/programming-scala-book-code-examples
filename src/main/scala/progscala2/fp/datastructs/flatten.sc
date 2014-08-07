// src/main/scala/progscala2/fp/datastructs/flatmap.sc

val graph = List(
  "a", List("b1", "b2", "b3"), List("c1", List("c21", Nil, "c22"), Nil, "e")
)

def flatten(list: List[_]): List[_] = list flatMap {
  case head :: tail => head :: flatten(tail)
  case Nil => Nil
  case x => List(x)
}

println(flatten(graph))
