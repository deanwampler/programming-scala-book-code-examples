// src/script/scala/progscala3/contexts/GivenImports.scala

object O1:
  val name = "O1"
  def m(s: String) = s"$s, hello from $name"
  class C1
  class C2
  given c1: C1 = new C1
  given c2: C2 = new C2

import O1._             // Import everything EXCEPT the givens, c1 and c2
import O1.given         // Import ONLY the givens (of type C1 and C2)
import O1.{given, _}    // Import everything, givens and non-givens in O1
import O1.{given C1}    // Import just the given of type C1
import O1.c1            // Import just the given c1 of type C1

object O2:
  class C1
  class C2
  given C1 = new C1
  given C2 = new C2
  given intOrd: Ordering[Int] = summon[Ordering[Int]]
  given listOrd[T: Ordering]: Ordering[List[T]] = summon[Ordering[List[T]]]
