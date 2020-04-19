// BEGIN FOR1
// src/script/scala/progscala3/patternmatching/MatchBoolean.scala

val bools = Seq(true, false)

for bool <- bools do
  bool match {
    case true => println("Got heads")
    // case false => println("Got tails")
  }
// END FOR1

// BEGIN FOR_IF
for bool <- bools do
  val which = if bool then "head" else "tails"
  println("Got " + which)

// END FOR_IF

// BEGIN FOR_PARTIAL
bools foreach {
  case true => println("Got heads")
  case false => println("Got tails")
}
// END FOR_PARTIAL

// BEGIN FOR_BAD
for bool <- bools do
  case true => println("Got heads")
  case false => println("Got tails")
// END FOR_BAD
