// src/main/scala/ForComps/for-options-list.sc

val list: List[Option[Int]] = List(Some(10), None, Some(20))

// Returns: List[Int] = List(20, 40)
for {
  opt <- list    // <1>
  i   <- opt     // <2>
  i2  =  2 * i   // <3>
} yield i2       // <4>
