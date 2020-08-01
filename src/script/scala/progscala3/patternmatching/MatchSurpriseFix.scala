// src/script/scala/progscala3/patternmatching/MatchSurpriseFix.scala

def checkY(y: Int): Seq[String] =
  for
    x <- Seq(99, 100, 101)
  yield
    x match
      case `y` => "found y!"    // Note the backticks
      case i: Int => "int: "+i

assert(checkY(99)  == Seq("found y!", "int: 100", "int: 101"))
assert(checkY(100) == Seq("int: 99", "found y!", "int: 101"))
assert(checkY(101) == Seq("int: 99", "int: 100", "found y!"))
assert(checkY(102) == Seq("int: 99", "int: 100", "int: 101"))
