// src/main/scala/PatternMatching/match-boolean.sc

val bools = List(true, false)

for (bool <- bools) {
  bool match {
    case true => println("Got heads")
    case false => println("Got tails")
  }
}

for (bool <- bools) {
  val which = if (bool == true) "head" else "tails"
  println("Got " + which)
}
