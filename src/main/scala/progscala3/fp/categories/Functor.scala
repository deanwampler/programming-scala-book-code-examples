// src/main/scala/progscala3/fp/categories/Functor.scala
package progscala3.fp.categories

trait Functor[F[_]]:                                                 // <1>
  def map[A, B](fa: F[A])(f: A => B): F[B]                           // <2>

object SeqF extends Functor[Seq]:                                    // <3>
  def map[A, B](seq: Seq[A])(f: A => B): Seq[B] = seq map f

object OptionF extends Functor[Option]:
  def map[A, B](opt: Option[A])(f: A => B): Option[B] = opt map f
