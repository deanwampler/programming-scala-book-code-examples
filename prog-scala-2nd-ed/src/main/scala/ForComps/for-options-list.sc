// src/main/scala/ForComps/for-options-list.sc

val list: List[Option[Int]] = List(Some(10), None, Some(20))

val list2a = for {
  Some(i) <- list
} yield (2 * i)
// Returns: list2a: List[Int] = List(20, 40)

// Translation step #1
val list2b = for {
  Some(i) <- list withFilter { 
    case Some(i) => true
    case None => false 
  }
} yield (2 * i)
// Returns: list2b: List[Int] = List(20, 40)

// Translation step #2
val list2c = list withFilter { 
  case Some(i) => true
  case None => false 
} map {
  case Some(i) => (2 * i)
}
// Returns: list2c: List[Int] = List(20, 40)
