// src/main/scala/progscala3/forcomps/for-guard.sc

val states = List("Alabama", "Alaska", "Virginia", "Wyoming")

val results1 = for {
  s <- states
  c <- s
  if c.isLower
} yield s"$c-${c.toUpper}"
println(results1)
assert(results1.take(4) == List("l-L", "a-A", "b-B", "a-A"))

val results2 = states flatMap (_.toSeq withFilter (_.isLower) map (c => s"$c-${c.toUpper}"))
println(results2)
assert(results1 == results2)
