// src/script/scala/progscala3/typesystem/typelambdas/Functor.scala
import progscala3.typesystem.typelambdas.Functor._

assert(List(1,2,3).map2(_ * 2) == List(2, 4, 6))
assert(Option(2).map2(_ * 2)   == Some(4))
val m = Map("one" -> 1, "two" -> 2, "three" -> 3)
assert(m.map2(_ * 2)           == Map("one" -> 2, "two" -> 4, "three" -> 6))
