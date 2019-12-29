// src/main/scala/progscala3/fp/basics/hofs-closure3-example.sc

object Multiplier {
  var factor = 2
  // Compare: val multiplier = (i: Int) => i * factor
  def multiplier(i: Int) = i * factor
}

val result1 = 
  (1 to 10) filter (_ % 2 == 0) map Multiplier.multiplier reduce (_ * _)
assert(result1 == 122880)

Multiplier.factor = 3
val result2 = 
  (1 to 10) filter (_ % 2 == 0) map Multiplier.multiplier reduce (_ * _)
assert(result2 == 933120)

