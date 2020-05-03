// src/script/scala/progscala3/forcomps/ForVariable.scala

val states = List("Alabama", "Alaska", "Virginia", "Wyoming")

val result1 = for
  s <- states
  c <- s
  if c.isLower
  c2 = s"$c-${c.toUpper}"
yield c2

val result2 = states flatMap {
	_.toSeq withFilter (_.isLower) map (c => s"$c-${c.toUpper}")
}
