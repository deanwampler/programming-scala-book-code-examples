// src/main/scala/ForComps/for-tries-list.sc

import scala.util.{ Try, Success, Failure }

val list: List[Try[Int]] = 
  List(Success(10), Failure(new RuntimeException("boo!")), Success(20))

val list2a = for {
  Success(i) <- list
} yield (2 * i)
// Returns: list2a: List[Int] = List(20, 40)
