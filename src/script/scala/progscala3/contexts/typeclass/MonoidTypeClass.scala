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
given NumericMonoid[T : Numeric]: Monoid[T] with
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

// tag::numericdefinition2[]
given NumericMonoid[T](using num: Numeric[T]): Monoid[T] with
  def unit: T = num.zero
  extension (t: T)
    infix def combine(other: T): T = num.plus(t, other)
// end::numericdefinition2[]

// tag::numericdefinition3[]
given [T : Numeric]: Monoid[T] with
  def unit: T = summon[Numeric[T]].zero
  extension (t: T)
    infix def combine(other: T): T = summon[Numeric[T]].plus(t, other)
// or
given [T](using num: Numeric[T]): Monoid[T] with
  def unit: T = summon[Numeric[T]].zero
  extension (t: T)
    infix def combine(other: T): T = summon[Numeric[T]].plus(t, other)

BigDecimal(3.14) <+> summon[Monoid[BigDecimal]].unit
summon[Monoid[BigDecimal]].unit <+> BigDecimal(3.14)
summon[Monoid[BigDecimal]].unit combine BigDecimal(3.14)
// end::numericdefinition3[]
