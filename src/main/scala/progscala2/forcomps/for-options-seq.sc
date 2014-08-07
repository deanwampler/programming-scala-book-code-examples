// src/main/scala/progscala2/forcomps/for-options-seq.sc

val results: Seq[Option[Int]] = Vector(Some(10), None, Some(20))

val results2a = for {
  Some(i) <- results
} yield (2 * i)
// Returns: results2a: Seq[Int] = Vector(20, 40)

// Translation step #1
val results2b = for {
  Some(i) <- results withFilter { 
    case Some(i) => true
    case None => false 
  }
} yield (2 * i)
// Returns: results2b: Seq[Int] = Vector(20, 40)

// Translation step #2
val results2c = results withFilter { 
  case Some(i) => true
  case None => false 
} map {
  case Some(i) => (2 * i)
}
// Returns: results2c: Seq[Int] = Vector(20, 40)
