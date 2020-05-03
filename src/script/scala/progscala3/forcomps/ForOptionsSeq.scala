// src/script/scala/progscala3/forcomps/ForOptionsSeq.scala

val options: Seq[Option[Int]] = Vector(Some(10), None, Some(20))

val results1 = for
  case Some(i) <- options
yield (2 * i)

val results2 = for
  case Some(i) <- options withFilter {
    case Some(i) => true
    case None => false
  }
yield (2 * i)

val results3 = options withFilter {
  case Some(i) => true
  case None => false
} map {
  case Some(i) => (2 * i)
  case None => -1             // <1>
}
