// src/script/scala/progscala3/traits/TraitConstruction.scala

trait M:
  print("M ")
  def m(s:String): Unit = println(s"M.m: $s")

trait T1 extends M:
  print(s"T1 (before: T1.t1 = $t1, ")
  val t1 = "T1"
  print(s"after: T1.t1 = $t1) ")
  abstract override def m(s:String): Unit = super.m(s"$s T1")

trait T2 extends M:
  print("T2 ")
  abstract override def m(s:String): Unit = super.m(s"$s T2")

class B extends M:
  print("B ")
  override def m(s:String): Unit = super.m(s"$s B")

class C extends B with T1 with T2:
  print(s"C (before: C.c = $c, ")
  val c="C"
  print(s"after:  C.c = $c) ")
  override def m(s:String): Unit = super.m(s"$s C")

val c = C()
println()  // Add a linefeed for all the print() statements.
c.m("Calling c.m:")

