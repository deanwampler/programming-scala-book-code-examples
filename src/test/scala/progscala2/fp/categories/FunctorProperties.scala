// src/test/scala/progscala2/fp/categories/FunctorProperties.scala
package progscala2.fp.categories
import org.scalatest.FunSpec
import org.scalatest.prop.PropertyChecks

class FunctorProperties extends FunSpec with PropertyChecks {

  def id[A] = identity[A] _    // Lift identity method to a function

  def testSeqMorphism(f2: Int => Int) = {                            // <1>
    val f1: Int => Int = _ * 2
    import SeqF._
    forAll { (l: List[Int]) =>
      assert( map(map(l)(f1))(f2) === map(l)(f2 compose f1) )
    }
  }

  def testFunctionMorphism(f2: Int => Int) = {                       // <2>
    val f1: Int => Int = _ * 2
    import FunctionF._
    forAll { (i: Int) =>
      assert( map(f1)(f2)(i) === (f2 compose f1)(i) )                // <3>
    }
  }

  describe ("Functor morphism composition") {                        // <4>
    it ("works for Sequence Functors") {
      testSeqMorphism(_ + 3)
    }
    it ("works for Function Functors") {
      testFunctionMorphism(_ + 3)
    }
  }

  describe ("Functor identity composed with a another function commutes") {
    it ("works for Sequence Functors") {                             // <5>
      testSeqMorphism(id[Int])
    }
    it ("works for Function Functors") {
      testFunctionMorphism(id)
    }
  }

  describe ("Functor identity maps between the identities of the categories") {
    it ("works for Sequence Functors") {                             // <6>
      val f1: Int => String = _.toString
      import SeqF._
      assert( map(List.empty[Int])(f1) === List.empty[String] )
    }
    it ("works for Function Functors") {
      val f1: Int => Int = _ * 2
      def id[A] = identity[A] _    // Lift method to a function
      import FunctionF._
      forAll { (i: Int) =>
        assert( map(id[Int])(f1)(i) === (f1 compose id[Int])(i) )
      }
    }
  }

  describe ("Functor morphism composition is associative") {
    it ("works for Sequence Functors") {                             // <7>
      val f1: Int => Int = _ * 2
      val f2: Int => Int = _ + 3
      val f3: Int => Int = _ * 5
      import SeqF._
      forAll { (l: List[Int]) =>
        val m12 = map(map(l)(f1))(f2)
        val m23 = (seq: Seq[Int]) => map(map(seq)(f2))(f3)
        assert( map(m12)(f3) === m23(map(l)(f1)) )
      }
    }
    it ("works for Function Functors") {
      val f1: Int => Int = _ * 2
      val f2: Int => Int = _ + 3
      val f3: Int => Int = _ * 5
      val f:  Int => Int = _ + 21
      import FunctionF._
      val m12 = map(map(f)(f1))(f2)
      val m23 = (g: Int => Int) => map(map(g)(f2))(f3)
      forAll { (i: Int) =>
        assert( map(m12)(f3)(i) === m23(map(f)(f1))(i) )
      }
    }
  }
}
