// tag::usage[]
// src/script/scala/progscala3/contexts/typeclass/MonoidTypeClass.scala
import progscala3.contexts.typeclass.{Monoid, given}       // <1>

"2" <+> ("3" <+> "4")             // "234"
("2" <+> "3") <+> "4"             // "234"
("2" combine "3") combine "4"     // "234"
StringMonoid.unit <+> "2"         // "2"
"2" <+> StringMonoid.unit         // "2"

2 <+> (3 <+> 4)                   // 9
(2 <+> 3) <+> 4                   // 9
(2 combine 3) combine 4           // 9
IntMonoid.unit <+> 2              // 2
2 <+> IntMonoid.unit              // 2
// end::usage[]

// tag::numericdefinition[]
// 2025-06-16: The following line was the original syntax in Scala 3
// given NumericMonoid[T : Numeric]: Monoid[T]:
// This is the currently acceptable syntax:
given NumericMonoid: [T: Numeric] => Monoid[T]:
  def unit: T = summon[Numeric[T]].zero
  extension (t: T)
    infix def combine(other: T): T = summon[Numeric[T]].plus(t, other)

2.2 <+> (3.3 <+> 4.4)             // 9.9
(2.2 <+> 3.3) <+> 4.4             // 9.9
(2.2 combine 3.3) combine 4.4     // 9.9

BigDecimal(3.14) <+> NumericMonoid.unit               // <1>
NumericMonoid[BigDecimal].unit <+> BigDecimal(3.14)
NumericMonoid[BigDecimal].unit combine BigDecimal(3.14)
// end::numericdefinition[]

// See MonoidTypeClass2.scala for an alternative definition.
// We can't write it in this file because loading the script causes errors
// with alternative definitions in scope.
