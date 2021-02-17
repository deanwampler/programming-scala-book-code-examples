// src/main/scala/progscala3/fp/categories/Functor2.scala
package progscala3.fp.categories

/**
 * A more details exploration of functors. This code is mentioned in the book,
 * but not shown.
 */

/**
 * Unlike the Functor definition before, this implementation generalizes the
 * input and output "categories" (collections) allowing them to be different,
 * and it generalizes the tranformation function from A => B to some
 * Transform[A,B].
 */
trait Functor2[F1[_], F2[_]]:
  type Transform[A,B]
  def map[A, B](fa: F1[A])(f: Transform[A,B]): F2[B]

/**
 * The same as for the original Functor implementation, but with the new
 * generalizations. Try doing a [Seq,Set] variant.
 */
object SeqF2 extends Functor2[Seq,Seq]:
  type Transform[A,B] = A => B
  def map[A, B](seq: Seq[A])(f: A => B): Seq[B] = seq map f

object OptionF2 extends Functor2[Option,Option]:
  type Transform[A,B] = A => B
  def map[A, B](opt: Option[A])(f: A => B): Option[B] = opt map f

@main def TryFunctor2() =
  val fii: Int => Int = _ * 2
  println(s"SeqF2.map(Seq(1,2,3,4))(fii)         = ${SeqF2.map(Seq(1,2,3,4))(fii)}")
  println(s"SeqF2.map(Seq.empty[Int])(fii)       = ${SeqF2.map(Seq.empty[Int])(fii)}")
  println(s"OptionF2.map(Some(2))(fii)           = ${OptionF2.map(Some(2))(fii)}")
  println(s"OptionF2.map(Option.empty[Int])(fii) = ${OptionF2.map(Option.empty[Int])(fii)}")

  assert(SeqF2.map(Seq(1,2,3,4))(fii)   == Seq(2, 4, 6, 8))
  assert(SeqF2.map(Seq.empty[Int])(fii) == Nil)
  assert(OptionF2.map(Some(2))(fii) == Some(4))
  assert(OptionF2.map(Option.empty[Int])(fii) == None)
end TryFunctor2

/**
 * A first ("A") attempt at implementing a true categorical Functor, which can
 * transform arrows (functions) from one category to another. This yields a
 * complicated Transform function and it has a significant flaw; it requires
 * a mapping from A2 -> A1, while real functors would go the other way. What
 * this attempt does is leverage the composition law that
 *   A2 => B2 == A2 => (A1 => B1) => B2
 * If you have the original function, A1 => B1, and new functions A2 => A1
 * and B1 => B2. Note the signature of the second map method. Finally, the
 * type parameters for the class are wierd.
 */
object FunctionF2A:
    type F1 = [B] =>> [X] =>> X => B
    type F2 = [A] =>> [X] =>> A => X
    type Transf = [A, B] =>> [X, Y] =>> A => (X => B) => Y

class FunctionF2A[A2,B1]
    extends Functor2[FunctionF2A.F1[B1], FunctionF2A.F2[A2]]:
  import FunctionF2A.*
  type Transform = [X, Y] =>> Transf[A2, B1][X, Y]
  type F1B = [X] =>> F1[B1][X]
  type F2A = [X] =>> F1[A2][X]

  def map[A1, B2](f: F1B[A1])(t: Transform[A1, B2]): A2 => B2 =
    (a2: A2) => t(a2)(f)

  def map[A1, B2](func: A1 => B1)(fa: A2 => A1)(fb: B1 => B2): A2 => B2 =
    val transform = (a2: A2) => (f: A1 => B1) => fb(f(fa(a2)))
    map(func)(transform)
end FunctionF2A

@main def TryFunctionF2A() =
  val list = (0 to 100 by 10).toList
  val fd2bd: Double => BigDecimal = d => BigDecimal(d)
  val fi2d: Int => Double = 1.1 * _
  val fbd2s: BigDecimal => String = _.toString
  val ff2a = FunctionF2A[Int,BigDecimal]
  val fi2s: Int => String = ff2a.map(fd2bd)(fi2d)(fbd2s)
  val newList = list.map(fi2s)
  println(s"Input list: $list")
  println(s"Output list: $newList")
  assert(newList == // ouch:
    List("0.0", "11.0", "22.0", "33.0", "44.0", "55.00000000000001",
      "66.0", "77.0", "88.0", "99.00000000000001", "110.00000000000001"))
end TryFunctionF2A

/**
 * Now let's move to something closer to how functors work in categories, the "B"
 * implementation. (I won't implement Functor2 from this point on, but follow
 * "its example".)
 * If you think of the Ints as a category, and similarly for other types, then
 * an arrow in the Ints is f: Int => Int. One transformation we could write is
 * lift all such arrows to F[Int] => F[Int]. Let's explore that. (The book
 * discussion also shows a simpler way to do this.) For any F[A], we'll find it
 * useful to have flatMap, so we'll introduce a trait for it use givens for
 * specific collection types.
 */
object FunctionF2B:
  trait FlatMap[A, F[A]]:
    def apply(fa: F[A])(f: A => F[A]): F[A]

  def map[A, F[A]](f: A => A)(t: A => F[A])(using flatMap: FlatMap[A, F]): F[A] => F[A] =
    (fa: F[A]) => flatMap(fa)(t compose f)

@main def TryFunctionF2B() =
  given [A]: FunctionF2B.FlatMap[A,Seq] with
    def apply(seq: Seq[A])(f: A => Seq[A]): Seq[A] = seq.flatMap(f)
  given [A]: FunctionF2B.FlatMap[A,Option] with
    def apply(seq: Option[A])(f: A => Option[A]): Option[A] = seq.flatMap(f)

  val f: Int => Int = 2 * _
  def tseq: Int => Seq[Int] = i => Seq(i)
  def topt: Int => Option[Int] = i => Option(i)
  val fseq2b = FunctionF2B.map(f)(tseq)
  val fopt2b = FunctionF2B.map(f)(topt)

  println(s"fseq2b(List(1,2,3,4,5,6)) = ${fseq2b(List(1,2,3,4,5,6))}")
  println(s"fopt2b(Some(3))           = ${fopt2b(Some(3))}")
  println(s"fopt2b(None)              = ${fopt2b(None)}")
  assert(fseq2b(List(1,2,3,4,5,6)) == List(2, 4, 6, 8, 10, 12))
  assert(fopt2b(Some(3)) == Some(6))
  assert(fopt2b(None) == None)
end TryFunctionF2B

/**
 * Next, let's generalize the transformation of a function F[A] => F[A]
 * to G[A] => G[A]. This requires a FlatMap given, like before, plus a "lifter"
 * to convert an A to an F[A].
 */
object FunctionF2C:
  import FunctionF2B.FlatMap

  trait Lift[A, F[A]]:
    def apply(a: A): F[A]

  def map[A, F[A], G[A]](f: F[A] => F[A])(t: F[A] => G[A])(
      using flatMap: FlatMap[A, G], lift: Lift[A, F]): G[A] => G[A] =
    (ga: G[A]) => flatMap(ga) { a =>
      val fa = lift(a)
      val fa2 = f(fa)
      t(fa2)
    }

@main def TryFunctionF2C() =
  given [A]: FunctionF2C.Lift[A,Seq] = (a:A) => Seq(a)
  given [A]: FunctionF2B.FlatMap[A,Set] with
    def apply(set: Set[A])(f: A => Set[A]): Set[A] = set.flatMap(f)

  val fseqd: Seq[Double] => Seq[Double] = _.map(2.0 * _)
  val fs2s: Seq[Double] => Set[Double] = _.toSet
  val t2c: Set[Double] => Set[Double] = FunctionF2C.map(fseqd)(fs2s)
  val set: Set[Double] = Set(0.0, 1.1, 2.2, 3.3, 4.4, 5.5)
  val newSet = t2c(set)
  println(s"Input set: $set")
  println(s"Output set: $newSet")
  assert(newSet == Set(0.0, 2.2, 8.8, 4.4, 11.0, 6.6))

/**
 * Finally, let's fully generalize the transformation of a function F[A] => F[A]
 * to G[B] => G[B]. Now we need a "bijection" between A <=> B, using a pair of
 * functions for this purpose. We'll also use the simpler Functor and SeqF
 * defined in Functor.scala:
 */

import progscala3.fp.categories.{Functor, SeqF}

object FunctionF2D:
  import FunctionF2B.FlatMap
  import FunctionF2C.Lift

  def map[A, B, F[A], G[B]](f: F[A] => F[A])(
      tab: A => B)(tba: B => A)(tfg: F[B] => G[B])(
      using flatMapG: FlatMap[B, G], functor: Functor[F],
      lift: Lift[A, F]): G[B] => G[B] =
    (gb: G[B]) => flatMapG(gb) { b =>
      val fa = lift(tba(b))
      val fa2 = f(fa)
      val fb = functor.map[A,B](fa2)(tab)
      tfg(fb)
    }

@main def TryFunctionF2D() =
  given [A]: FunctionF2C.Lift[A,Seq] = (a:A) => Seq(a)
  given [A]: FunctionF2B.FlatMap[A,Set] with
    def apply(set: Set[A])(f: A => Set[A]): Set[A] = set.flatMap(f)
  given Functor[Seq] = SeqF

  val fsd2sd: Seq[Double] => Seq[Double] = _.map(_ * 1.1)
  val td2bd: Double => BigDecimal = d => BigDecimal(d)
  val tbd2d: BigDecimal => Double = bd => bd.doubleValue
  val tsbd2sbd: Seq[BigDecimal] => Set[BigDecimal] = _.toSet
  val t2d: Set[BigDecimal] => Set[BigDecimal] =
    FunctionF2D.map(fsd2sd)(td2bd)(tbd2d)(tsbd2sbd)

  val set2d: Set[BigDecimal] =
    Set(0.0, 1.1, 2.2, 3.3, 4.4, 5.5).map(d => BigDecimal(d))
  val newSet2d = t2d(set2d)
  println(s"Input set: $set2d")
  println(s"Output set: $newSet2d")
  assert(newSet2d ==
    Set(6.050000000000001, 2.4200000000000004, 1.2100000000000002,
      3.63, 4.840000000000001, 0.0).map(d => BigDecimal(d)))
end TryFunctionF2D
