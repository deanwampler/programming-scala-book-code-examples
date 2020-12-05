// tag::part1[]
// src/script/scala/progscala3/meta/inline/Overrides.scala

trait T:
  def m1: String
  def m2: String = m1

object O extends T:
  inline def m1 = "O.m1"
  override inline def m2 = m1 + " called from O.m2"

val t: T = O
assert(O.m1 == t.m1)
assert(O.m2 == t.m2)
// end::part1[]

// tag::part2[]
trait T2:
  inline def m: String

object O2 extends T2:
  inline def m: String = "O2.m"

val t2: T2 = O2
O2.m
t2.m       // ERROR
// end::part2[]
