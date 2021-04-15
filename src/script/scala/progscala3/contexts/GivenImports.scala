// tag::O1[]
// src/script/scala/progscala3/contexts/GivenImports.scala

object O1:
  val name = "O1"
  def m(s: String) = s"$s, hello from $name"
  class C1
  class C2
  given c1: C1 = C1()
  given c2: C2 = C2()
// end::O1[]

// tag::imports[]
import O1.*             // Import everything EXCEPT the givens, c1 and c2
import O1.given         // Import ONLY the givens (of type C1 and C2)
import O1.{given, *}    // Import everything, givens and nongivens in O1
import O1.{given C1}    // Import just the given of type C1
import O1.c2            // Import just the given c2 of type C2
// end::imports[]

// tag::O2[]
trait Marker[T]
object O2:
  class C1
  given C1 = C1()
  given Marker[Int] with {}                 // <1>
  given Marker[List[?]] with {}             // <2>

import O2.{given Marker[?]}     // Import all given Markers
import O2.{given Marker[Int]}   // Import just the Marker[Int]
// end::O2[]
