// src/main/scala/PatternMatching/match-boolean.sc

val bools = List(true, false)

for (bool <- bools) {
  bool match {
    case true => println("heads")
    case false => println("tails")
  }
}

for (bool <- bools) {
  println(if (bool == true) "head" else "tails")
}
