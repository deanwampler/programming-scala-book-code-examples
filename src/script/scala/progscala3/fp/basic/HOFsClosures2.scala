// src/script/scala/progscala3/fp/basics/HOFsClosures2.scala

object Multiplier:
  var factor = 2

val multiplier = (i: Int) => i * Multiplier.factor

val result1 = (1 to 10).filter(_ % 2 == 0).map(multiplier).reduce(_ * _)
assert(result1 == 122880)

Multiplier.factor = 3
val result2 = (1 to 10).filter(_ % 2 == 0).map(multiplier).reduce(_ * _)
assert(result2 == 933120)

object Multiplier2:
  var factor = 2
  // Compare: val multiplier = (i: Int) => i * factor
  def multiplier(i: Int) = i * factor

val result3 =
	(1 to 10).filter(_ % 2 == 0).map(Multiplier2.multiplier).reduce(_ * _)
sert(result3 == 122880)

Multiplier2.factor = 3
val result4 =
	(1 to 10).filter(_ % 2 == 0).map(Multiplier2.multiplier).reduce(_ * _)
assert(result4 == 933120)
