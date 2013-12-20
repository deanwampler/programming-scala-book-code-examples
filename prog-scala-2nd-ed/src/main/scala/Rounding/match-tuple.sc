// code-examples/Rounding/match-tuple-script.scala

val tupA = ("Good", "Morning!")
val tupB = ("Guten", "Tag!")

for (tup <- List(tupA, tupB)) {
  tup match {
    case (thingOne, thingTwo) if thingOne == "Good" =>
        println("A two-tuple starting with 'Good'.")
    case (thingOne, thingTwo) =>
        println("This has two things: " + thingOne + " and " + thingTwo)
  }
}