// tag::definitions[]
// src/script/scala/progscala3/contexts/MonoidTypeClass.scala
import scala.annotation.{alpha, infix}

trait Semigroup[T]:
  extension (t: T):
    @infix def combine(other: T): T                                  // <1>
    @alpha("plus") def <+>(other: T): T = t.combine(other)

trait Monoid[T] extends Semigroup[T]:
  def unit: T                                                        // <2>

given StringMonoid as Monoid[String]:                                // <3>
  def unit: String = ""
  extension (s: String) @infix def combine(other: String): String = s + other

given IntMonoid as Monoid[Int]:
  def unit: Int = 0
  extension (i: Int) @infix def combine(other: Int): Int = i + other
// end::definitions[]

// tag::usage[]
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
given NumericMonoid[T](using num: Numeric[T]) as Monoid[T]:
  def unit: T = num.zero
  extension (t: T) @infix def combine(other: T): T = num.plus(t, other)

2.2 <+> (3.3 <+> 4.4)             // 9.9
(2.2 <+> 3.3) <+> 4.4             // 9.9
(2.2 combine 3.3) combine 4.4     // 9.9

BigDecimal(3.14) <+> NumericMonoid.unit
NumericMonoid[BigDecimal].unit <+> BigDecimal(3.14)
NumericMonoid[BigDecimal].unit combine BigDecimal(3.14)
// end::numericdefinition[]

// tag::numericdefinition2[]
given [T](using num: Numeric[T]) as Monoid[T]:
  def unit: T = num.zero
  extension (t: T) @infix def combine(other: T): T = num.plus(t, other)

2.2 <+> (3.3 <+> 4.4)             // 9.9
(2.2 <+> 3.3) <+> 4.4             // 9.9
(2.2 combine 3.3) combine 4.4     // 9.9

BigDecimal(3.14) <+> summon[Monoid[BigDecimal]].unit
summon[Monoid[BigDecimal]].unit <+> BigDecimal(3.14)
summon[Monoid[BigDecimal]].unit combine BigDecimal(3.14)
// emd::numericdefinition2[]

