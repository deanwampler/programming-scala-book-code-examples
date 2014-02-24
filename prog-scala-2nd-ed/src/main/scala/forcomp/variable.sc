// src/main/scala/forcomp/flatmap.sc

val states = List("Alabama", "Alaska", "Virginia", "Wyoming")

for {
  s <- states
  c <- s
  if c.isLower
  c2 = s"$c-${c.toUpper} "
} yield c2

states flatMap (_.toSeq filter (_.isLower) map { c => 
  val c2 = s"$c-${c.toUpper} "
  c2
})
