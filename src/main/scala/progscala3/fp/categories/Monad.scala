// tag::definition[]
// src/main/scala/progscala3/fp/categories/Monad.scala
package progscala3.fp.categories
import scala.annotation.targetName

trait Monad[M[_]]:                                                 // <1>
  def flatMap[A, B](fa: M[A])(f: A => M[B]): M[B]                  // <2>
  def unit[A](a: => A): M[A]                                       // <3>

object SeqM extends Monad[Seq]:
  def flatMap[A, B](seq: Seq[A])(f: A => Seq[B]): Seq[B] =
    seq flatMap f
  def unit[A](a: => A): Seq[A] = Seq(a)

object OptionM extends Monad[Option]:
  def flatMap[A, B](opt: Option[A])(f: A => Option[B]):Option[B]=
    opt flatMap f
  def unit[A](a: => A): Option[A] = Option(a)
// end::definition[]

// Some common aliases for the methods that you'll see in some math texbooks
// and other languages, like Haskell. (This part is not shown in the book).
trait MonadPlus[M[_]] extends Monad[M]:
  def bind[A,B](fa: M[A])(f: A => M[B]): M[B] = flatMap(fa)(f)     // <4>
  @targetName("rocket")
  def >>=[A,B](fa: M[A])(f: A => M[B]): M[B] = flatMap(fa)(f)
  def pure[A](a: => A): M[A] = unit(a)
  def `return`[A](a: => A): M[A] = unit(a)    // backticks to avoid keyword
