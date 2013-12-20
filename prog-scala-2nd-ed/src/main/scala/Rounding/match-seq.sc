// code-examples/Rounding/match-seq-script.scala

val willWork = List(1, 3, 23, 90)
val willNotWork = List(4, 18, 52)
val empty = List()

for (l <- List(willWork, willNotWork, empty)) {
  l match {
    case List(_, 3, _, _) => println("Four elements, with the 2nd being '3'.")
    case List(_*) => println("Any other list with 0 or more elements.")
  }
}
