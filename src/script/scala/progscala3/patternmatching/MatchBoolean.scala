// src/script/scala/progscala3/patternmatching/MatchBoolean.scala

val bools = Seq(true, false)

for bool <- bools do
  bool match
    case true  => println("Got heads")
    case false => println("Got tails")

for bool <- bools do
  val which = if bool then "head" else "tails"
  println("Got " + which)

// The first example is the same as this foreach with
// partial function:
bools foreach {
  case true => println("Got heads")
  case false => println("Got tails")
}
