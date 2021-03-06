// src/script/scala/progscala3/typesystem/typelambdas/Functor.scala
import progscala3.typesystem.typelambdas.Functor.given

Seq(1,2,3).map2(_ * 2.2)
Nil.map2(_.toString)

Map("one" -> 1, "two" -> 2, "three" -> 3).map2(_ * 2.2)
Map.empty[String,Int].map2(_.toString)
