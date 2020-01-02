// src/main/scala/progscala3/forcomps/for-variable.sc

val states = List("Alabama", "Alaska", "Virginia", "Wyoming")

val result1 = for {
  s <- states
  c <- s
  if c.isLower
  c2 = s"$c-${c.toUpper}"
} yield c2
println(result1)
// Check the first five values:
assert(result1.take(5) == List("l-L", "a-A", "b-B", "a-A", "m-M"))

val result2 = states flatMap (_.toSeq withFilter (_.isLower) map { c => 
  val c2 = s"$c-${c.toUpper}"
  c2
})
println(result2)
// Check the first five values:
assert(result2.take(5) == List("l-L", "a-A", "b-B", "a-A", "m-M"))
