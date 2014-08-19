// src/test/scala/progscala2/fp/categories/MonadProperties.scala
package progscala2.fp.categories
import org.scalatest.FunSpec
import org.scalatest.prop.PropertyChecks

class MonadProperties extends FunSpec with PropertyChecks {

  // Arbitrary function:
  val f1: Int => Seq[Int] = i => 0 until 10 by ((math.abs(i) % 10) + 1)

  describe ("Monad law for unit") {
    it ("works for Sequence Monads") {
      import SeqM._
      val unitInt: Int => Seq[Int] = (i:Int) => unit(i)
      forAll { (i: Int) =>
        val seq: Seq[Int] = Seq(i)
        assert( flatMap(unit(i))(f1)  === f1(i) )
        assert( flatMap(seq)(unitInt) === seq )
      }
    }
  }

  describe ("Monad law for function composition") {
    it ("works for Sequence Monads") {
      val f2: Int => Seq[Int] = i => Seq(i+1)
      import SeqM._
      forAll { (i: Int) =>
        val seq = Seq(i)
        assert( flatMap(flatMap(seq)(f1))(f2) ===
                flatMap(seq)(x => flatMap(f1(x))(f2)) )
      }
    }
  }
}