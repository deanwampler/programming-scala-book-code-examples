// src/main/scala/progscala3/contexts/GenericExtensionMethods.scala

package progscala3.contexts

object GenericExtensionMethods:
  type Eq[T] = CanEqual[T, T]                                        // <1>

  extension [A: Ordering](seq: Seq[A])                               // <2>
    def sortedUnique: Seq[A] =
      if seq.size == 0 then seq
      else
        val sorted = seq.sorted                                      // <3>
        sorted.foldLeft(Vector(sorted.head)) { (vect, a) =>
          if vect.last == a then vect else vect :+ a
        }
