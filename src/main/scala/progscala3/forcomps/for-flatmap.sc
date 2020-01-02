// src/main/scala/progscala3/forcomps/for-flatmap.sc

val states = List("Alabama", "Alaska", "Virginia", "Wyoming")

val results1 = for {
  s <- states
  c <- s
} yield s"$c-${c.toUpper}"
println(results1)
assert(results1.take(4) == List("A-A", "l-L", "a-A", "b-B"))

val results2 = states flatMap (_.toSeq map (c => s"$c-${c.toUpper}"))
println(results2)
assert(results2 == results1)
