// src/main/scala/Rounding/match-boolean.sc

val bools = List(true, false)

for (bool <- bools) {
  bool match {
    case true => println("heads")
    case false => println("tails")
    case _ => println("something other than heads or tails (yikes!)")
  }
}
