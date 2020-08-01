// src/test/scala/progscala3/fp/categories/FunctorPropertiesSuite.scala
package progscala3.fp.categories

import munit.ScalaCheckSuite
import org.scalacheck._

class FunctorPropertiesSuite extends ScalaCheckSuite:
  import Prop.forAll

  def id[A] = identity[A]    // Lift identity method to a function

  def testSeqMorphism(f2: Int => Int) =                              // <1>
    val f1: Int => Int = _ * 2
    import SeqF._
    forAll { (l: List[Int]) =>
      map(map(l)(f1))(f2) == map(l)(f2 compose f1)
    }

  def testFunctionMorphism(f2: Int => Int) =                         // <2>
    val f1: Int => Int = _ * 2
    import FunctionF._
    forAll { (i: Int) =>
      map(f1)(f2)(i) == (f2 compose f1)(i)                           // <3>
    }

  property("Functor morphism composition") {
    testSeqMorphism(_ + 3)
    testFunctionMorphism(_ + 3)
  }

  property("Functor identity composition") {
    testSeqMorphism(id[Int])
    testFunctionMorphism(id)
  }

  property("Functor identity maps between the category identities") {
    val f1: Int => String = _.toString
    val f2: Int => Int = _ * 2
    def id[A] = identity[A]     // Lift method to a function

    {  // scope the import:
      import SeqF._
      map(List.empty[Int])(f1) == List.empty[String]
    }

    {
      import FunctionF._
      forAll { (i: Int) =>
        map(id[Int])(f2)(i) == (f2 compose id[Int])(i)
      }
    }
  }

  property("Functor morphism composition is associative") {
    val f1: Int => Int = _ * 2
    val f2: Int => Int = _ + 3
    val f3: Int => Int = _ * 5
    val f:  Int => Int = _ + 21

    {  // scope the import:
      import SeqF._
      forAll { (l: List[Int]) =>
        val m12 = map(map(l)(f1))(f2)
        val m23 = (seq: Seq[Int]) => map(map(seq)(f2))(f3)
        map(m12)(f3) == m23(map(l)(f1))
      }
    }
    {
      import FunctionF._
      val m12 = map(map(f)(f1))(f2)
      val m23 = (g: Int => Int) => map(map(g)(f2))(f3)
      forAll { (i: Int) =>
        map(m12)(f3)(i) == m23(map(f)(f1))(i)
      }
    }
  }