// tag::bad[]
// src/script/scala/progscala3/patternmatching/MatchSurprise.scala

def checkY(y: Int): Seq[String] =
  for x <- Seq(99, 100, 101)
  yield x match
    case y => "found y!"
    case i: Int => "int: "+i  // ERROR: Unreachable code
// end::bad[]

// tag::good1[]
def checkY2(Y: Int): Seq[String] =
  for x <- Seq(99, 100, 101)
  yield x match
    case Y => "found y!"
    case i: Int => "int: "+i
// tag::good1[]


// tag::good2[]
def checkY3(y: Int): Seq[String] =
  for x <- Seq(99, 100, 101)
  yield x match
    case `y` => "found y!"
    case i: Int => "int: "+i
// tag::good2[]
