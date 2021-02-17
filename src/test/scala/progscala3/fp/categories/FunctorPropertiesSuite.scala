// src/test/scala/progscala3/fp/categories/FunctorPropertiesSuite.scala
package progscala3.fp.categories

import munit.ScalaCheckSuite
import org.scalacheck.*

class FunctorPropertiesSuite extends ScalaCheckSuite:
  import Prop.forAll

  def id[A] = identity[A]    // Lift identity method to a function
  val f: Double => BigDecimal = d => BigDecimal(d)
  val fa: Int => Double = i => 1.5 * i
  val fb: BigDecimal => String = _.toString

  def testSeqMorphism(f2: Int => Int) =
    val f1: Int => Int = _ * 2
    import SeqF.*
    forAll { (l: List[Int]) =>
      map(map(l)(f1))(f2) == map(l)(f2 compose f1)
    }

  property("Functor morphism composition") {
    testSeqMorphism(_ + 3)
  }

  property("Functor identity composition") {
    testSeqMorphism(id[Int])
  }

  property("Functor identity maps between the category identities") {
    val f1: Int => String = _.toString
    val f2: Int => Int = _ * 2
    def id[A] = identity[A]     // Lift method to a function

    {
      import SeqF.*   // scope the import:
      map(List.empty[Int])(f1) == List.empty[String]
    }
  }

  property("Functor morphism composition is associative") {
    val f1: Int => Int = _ * 2
    val f2: Int => Int = _ + 3
    val f3: Int => Int = _ * 5
    val f:  Int => Int = _ + 21

    {  // scope the import:
      import SeqF.*
      forAll { (l: List[Int]) =>
        val m12 = map(map(l)(f1))(f2)
        val m23 = (seq: Seq[Int]) => map(map(seq)(f2))(f3)
        map(m12)(f3) == m23(map(l)(f1))
      }
    }
  }
