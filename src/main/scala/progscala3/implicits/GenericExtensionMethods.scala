// src/main/scala/progscala3/implicits/GenericExtensionMethods.scala

package progscala3.implicits

object GenericExtensionMethods:
  def [A](seq: Seq[A]) sortedUnique(using Ordering[A]): Seq[A] = // <1>
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

  def [A : Ordering](seq: Seq[A]) sortedUnique2: Seq[A] =        // <3>
    seq.sortedUnique
