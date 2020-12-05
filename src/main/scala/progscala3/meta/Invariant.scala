// src/main/scala/progscala3/meta/Invariant.scala
package progscala3.meta
import scala.quoted._                                           // <1>

object invariant:
  inline val ignore = false

  inline def apply[T](
      inline predicate: => Boolean)(
      inline block: => T): T =
    if !ignore then
      if !predicate then fail(predicate, block, "before")       // <2>
      val result = block
      if !predicate then fail(predicate, block, "after")
      result
    else
      block

  inline private def fail[T](
      inline predicate: => Boolean,
      inline block: => T,
      inline beforeAfter: String): Unit =
    ${ failImpl('predicate, 'block, 'beforeAfter) }             // <3>

  case class InvariantFailure(msg: String) extends RuntimeException(msg)

  def failImpl[T](
      predicate: Expr[Boolean], block: Expr[T], beforeAfter: Expr[String])(
      using Quotes): Expr[String] =                             // <4>
    '{ throw InvariantFailure("FAILURE! predicate " + ${showExpr(predicate)}
      + " failed " + $beforeAfter
      + " evaluation of block: " + ${showExpr(block)})
    }

  /* Return a string for the expression */
  inline private def showExpr[T](expr: Expr[T])(using Quotes): Expr[String] =
    val code: String = expr.show                                // <5>
    Expr(code)
