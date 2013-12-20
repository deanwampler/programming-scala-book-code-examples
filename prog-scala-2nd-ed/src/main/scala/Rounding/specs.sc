// code-examples/Rounding/specs-script.scala
// Example fragment of a Specs script. Doesn't run standalone

"nerd finder" should {
  "identify nerds from a List" in {
    val actors = List("Rick Moranis", "James Dean", "Woody Allen")
    val finder = new NerdFinder(actors)
    finder.findNerds mustEqual List("Rick Moranis", "Woody Allen")
  }
}