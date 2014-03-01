// src/main/scala/ForComps/for-options-list2.sc

val list: List[Option[Int]] = List(Some(10), None, Some(20))

// Returns: List[Int] = List(20, 40)
for {
  Some(i) <- list
  i2  =  2 * i
} yield i2
