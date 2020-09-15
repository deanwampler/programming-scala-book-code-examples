// src/script/scala/progscala3/contexts/GivenImports.scala

object O1:
  val name = "O1"
  val m(s: String) = s"$s, hello from $name"
  class C1
  given c1 as C1
  class C2
  given c2 as C2

object O2:
  class C1
  given C1
  class C2
  given C2
  given intOrd as Ordering[Int]
  given listOrd[T: Ordering] as Ordering[List[T]]
