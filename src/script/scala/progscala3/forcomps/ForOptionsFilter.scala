// src/script/scala/progscala3/forcomps/ForOptionsFilter.scala

val options: Seq[Option[Int]] = Vector(Some(10), None, Some(20))

val results1 = for
  case Some(i) <- options
yield (2 * i)

// The following expressions are what you get if you expand the
// for comprehension according to the rules we discussed.

val results2 = for
  case Some(i) <- options.withFilter {
    case Some(i) => true
    case None => false
  }
yield (2 * i)

val results3 = options.withFilter {
  case Some(i) => true
  case None => false
}.map {
  case Some(i) => (2 * i)
  case None => -1             // hack to avoid a compiler warning
}
