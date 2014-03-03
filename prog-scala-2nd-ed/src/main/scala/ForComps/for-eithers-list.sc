// src/main/scala/ForComps/for-eithers-list.sc

import scala.util.{ Either, Left, Right }

val list: List[Either[RuntimeException,Int]] = 
  List(Right(10), Left(new RuntimeException("boo!")), Right(20))

val list2a = for {
  Right(i) <- list
} yield (2 * i)
// Returns: list2a: List[Int] = List(20, 40)
