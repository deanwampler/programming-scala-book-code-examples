// src/test/scala/progscala3/fp/categories/FunctorProperties.scala
package progscala3.fp.categories

import org.scalacheck._
import progscala3.metaprogramming.requirement

class FunctorProperties extends Properties("Functors") {
  import Prop.forAll

  def id[A] = identity[A]    // Lift identity method to a function

  def testSeqMorphism(f2: Int => Int) = {                            // <1>
    val f1: Int => Int = _ * 2
    import SeqF._
    forAll { (l: List[Int]) =>
      map(map(l)(f1))(f2) == map(l)(f2 compose f1)
    }
  }

  def testFunctionMorphism(f2: Int => Int) = {                       // <2>
    val f1: Int => Int = _ * 2
    import FunctionF._
    forAll { (i: Int) =>
      map(f1)(f2)(i) == (f2 compose f1)(i)                // <3>
    }
  }

  property("Functor morphism composition works for Sequence Functors") =
    testSeqMorphism(_ + 3)

  property("Functor morphism composition works for Function Functors") =
    testFunctionMorphism(_ + 3)

  property("Functor identity composed with a another function commutes for Sequence Functors") =
    testSeqMorphism(id[Int])

  property("Functor identity composed with a another function commutes for Sequence Functors") =
    testFunctionMorphism(id)

  property("Functor identity maps between the identities of the categories for Sequence Functors") = {
    val f1: Int => String = _.toString
    import SeqF._
    map(List.empty[Int])(f1) == List.empty[String]
  }

  property("Functor identity maps between the identities of the categories for Sequence Functors") = {
    val f1: Int => Int = _ * 2
    def id[A] = identity[A]     // Lift method to a function
    import FunctionF._
    forAll { (i: Int) =>
      map(id[Int])(f1)(i) == (f1 compose id[Int])(i)
    }
  }

  property("Functor morphism composition is associative for Sequence Functors") = {
    val f1: Int => Int = _ * 2
    val f2: Int => Int = _ + 3
    val f3: Int => Int = _ * 5
    import SeqF._
    forAll { (l: List[Int]) =>
      val m12 = map(map(l)(f1))(f2)
      val m23 = (seq: Seq[Int]) => map(map(seq)(f2))(f3)
      map(m12)(f3) == m23(map(l)(f1))
    }
  }

  property("Functor morphism composition is associative for Sequence Functors") = {
    val f1: Int => Int = _ * 2
    val f2: Int => Int = _ + 3
    val f3: Int => Int = _ * 5
    val f:  Int => Int = _ + 21
    import FunctionF._
    val m12 = map(map(f)(f1))(f2)
    val m23 = (g: Int => Int) => map(map(g)(f2))(f3)
    forAll { (i: Int) =>
      map(m12)(f3)(i) == m23(map(f)(f1))(i)
    }
  }
}
