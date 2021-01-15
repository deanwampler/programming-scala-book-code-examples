// tag::bad[]
// src/script/scala/progscala3/patternmatching/MatchSurprise.scala

def checkYBad(y: Int): Seq[String] =
  for x <- Seq(99, 100, 101)
  yield x match
    case y => "found y!"
    case i: Int => "int: "+i  // Unreachable case!
// end::bad[]

// tag::good1[]
def checkYGood1(Y: Int): Seq[String] =
  for x <- Seq(99, 100, 101)
  yield x match
    case Y => "found y!"
    case i: Int => "int: "+i
// end::good1[]


// tag::good2[]
def checkYGood2(y: Int): Seq[String] =
  for x <- Seq(99, 100, 101)
  yield x match
    case `y` => "found y!"
    case i: Int => "int: "+i
// end::good2[]
