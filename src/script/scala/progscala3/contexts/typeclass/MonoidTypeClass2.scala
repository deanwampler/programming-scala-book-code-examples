// tag::usage[]
// src/script/scala/progscala3/contexts/typeclass/MonoidTypeClass2.scala
import progscala3.contexts.typeclass.{Monoid, given}       // <1>

// 2025-06-16: This file was split off from MonoidTypeClass.scala to 
// to eliminate errors that occur with the effectively duplicate definitions
// of monoids for Numerics. Here, we use an anonymous given, rather than the
// named given in the other file. Note the use of summon below.

// tag::numericdefinition3[]
given [T : Numeric] => Monoid[T]:
  def unit: T = summon[Numeric[T]].zero
  extension (t: T)
    infix def combine(other: T): T = summon[Numeric[T]].plus(t, other)
// or
// 2025-06-16: This alternative syntax no longer works. A replacement is TBD.
// given [T](using num: Numeric[T]): Monoid[T]:
//   def unit: T = summon[Numeric[T]].zero
//   extension (t: T)
//     infix def combine(other: T): T = summon[Numeric[T]].plus(t, other)

BigDecimal(3.14) <+> summon[Monoid[BigDecimal]].unit
summon[Monoid[BigDecimal]].unit <+> BigDecimal(3.14)
summon[Monoid[BigDecimal]].unit combine BigDecimal(3.14)
// end::numericdefinition3[]
