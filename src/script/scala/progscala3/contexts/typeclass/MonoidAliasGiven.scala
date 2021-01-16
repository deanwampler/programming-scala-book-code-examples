// tag::definitions[]
// src/script/scala/progscala3/contexts/typeclass/MonoidAliasGiven.scala
import progscala3.contexts.typeclass.Monoid

given NumericMonoid2[T : Numeric]: Monoid[T] = new Monoid[T]:
  println("Initializing NumericMonoid2")
  def unit: T = summon[Numeric[T]].zero
  extension (t: T) infix def combine(other: T): T =
    summon[Numeric[T]].plus(t, other)

given StringMonoid2: Monoid[String] = new Monoid[String]:
  println("Initializing StringMonoid2")
  def unit: String = ""
  extension (s: String)
    infix def combine(other: String): String = s + other
// end::definitions[]

// tag::usage[]
2.2 <+> (3.3 <+> 4.4)             // 9.9
(2.2 <+> 3.3) <+> 4.4             // 9.9

"2" <+> ("3" <+> "4")             // "234"
("2" <+> "3") <+> "4"             // "234"
// end::usage[]
