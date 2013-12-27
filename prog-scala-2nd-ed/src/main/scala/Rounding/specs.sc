// src/main/scala/Rounding/specs.sc
// Example fragment of a Specs script. Doesn't run standalone

describe ("nerd finder") {
  it ("identify nerds from a List") {
    val actors = List("Rick Moranis", "James Dean", "Woody Allen")
    val finder = new NerdFinder(actors)
    finder.findNerds shouldEqual List("Rick Moranis", "Woody Allen")
  }
}
