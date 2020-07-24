// src/script/scala/progscala3/implicits/GenericExtensionMethods.scala

def [A : Ordering](seq: Seq[A]) sortUnique: Seq[A] =           // <1>
  if seq.size == 0
    seq
  else
    val sorted = seq.sorted
    sorted.foldLeft(Vector(sorted.head)) { (vect, a) =>
      if vect.last != a
        vect :+ a
      else
        vect
    }

val items1 = Seq(5, 1, 3, 1, 2, 2, 4, 3, 1, 4, 5)
val items2 = items1.map(_.toString)
items1.sortUnique
items2.sortUnique
