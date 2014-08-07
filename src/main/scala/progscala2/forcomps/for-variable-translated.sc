// src/main/scala/progscala2/forcomps/for-variable-translated.sc

val map = Map("one" -> 1, "two" -> 2)

val list1 = for {
  (key, value) <- map   // How is this line and the next translated?
  i10 = value + 10
} yield (i10)
// Result: list1: scala.collection.immutable.Iterable[Int] = List(11, 12)

// Translation:
val list2 = for {
  (i, i10) <- for {
    x1 @ (key, value) <- map
  } yield {
    val x2 @ i10 = value + 10
    (x1, x2)
  } 
} yield (i10)
// Result: list2: scala.collection.immutable.Iterable[Int] = List(11, 12)