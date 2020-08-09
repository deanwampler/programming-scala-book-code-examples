// src/script/scala/progscala3/patternmatching/MatchSurprise.scala

def checkY(y: Int): Seq[String] =
  for
    x <- Seq(99, 100, 101)
  yield
    x match
      case y => "found y!"      // <1>
      case i: Int => "int: "+i  // <2> ERROR: Unreachable code

checkY(100)
