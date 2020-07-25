// src/script/scala/progscala3/implicits/GenericExtensionMethods.scala

def [A](seq: Seq[A]) sortUnique(using Ordering[A]): Seq[A] =   // <1>
  if seq.size == 0
    seq
  else
    val sorted = seq.sorted                                    // <2>
    sorted.foldLeft(Vector(sorted.head)) { (vect, a) =>
      if vect.last != a
        vect :+ a
      else
        vect
    }

val items1 = Seq(5, 1, 3, 1, 2, 2, 4, 3, 1, 4, 5)
val items2 = items1.map(_.toString)
assert(items1.sortUnique == Vector(1, 2, 3, 4, 5))
assert(items2.sortUnique == Vector("1", "2", "3", "4", "5"))

def [A : Ordering](seq: Seq[A]) sortUnique2: Seq[A] =          // <3>
  seq.sortUnique

assert(items1.sortUnique2 == Vector(1, 2, 3, 4, 5))
assert(items2.sortUnique2 == Vector("1", "2", "3", "4", "5"))
