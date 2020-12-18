// tag::definitions[]
// src/main/scala/progscala3/contexts/typeclass/MonoidTypeClass.scala
package progscala3.contexts.typeclass
import scala.annotation.targetName

trait Semigroup[T]:
  extension (t: T)
    infix def combine(other: T): T                                   // <1>
    @targetName("plus") def <+>(other: T): T = t.combine(other)

trait Monoid[T] extends Semigroup[T]:
  def unit: T                                                        // <2>

given StringMonoid: Monoid[String] with                              // <3>
  def unit: String = ""
  extension (s: String) infix def combine(other: String): String = s + other

given IntMonoid: Monoid[Int] with
  def unit: Int = 0
  extension (i: Int) infix def combine(other: Int): Int = i + other
// end::definitions[]
