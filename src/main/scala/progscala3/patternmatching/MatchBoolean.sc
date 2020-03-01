// src/main/scala/progscala3/patternmatching/match-boolean.sc

val bools = Seq(true, false)

for (bool <- bools) {
  bool match {
    case true => println("Got heads")
    case false => println("Got tails")
  }
}

for (bool <- bools) {
  val which = if (bool) "head" else "tails"
  println("Got " + which)
}

// Note this simplification, where we pass a "partial function" instead:
bools foreach {
  case true => println("Got heads")
  case false => println("Got tails")
}
// But this doesn't work with the for loop. Scala can't be certain the
// types are correct.
// for (bool <- bools) {
//   case true => println("Got heads")
//   case false => println("Got tails")
// }

