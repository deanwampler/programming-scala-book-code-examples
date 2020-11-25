// src/main/scala/progscala3/metaprogramming/Invariant.scala
package progscala3.metaprogramming
import scala.quoted._

/**
 * Implement an invariant checker, which is supplied a predicate
 * that must be true both before and after the block is evaluated.
 * The result of the block is returned.
 */
object invariant:
  /**
   * When not testing and you want to eliminate the overhead of the
   * two predicate checks, set this global flag to true.
   */
  var skip = false

  /**
   * Throw an exception if the predicate is false before the block is
   * evaluatad, then evaluate the block, then check the predicate again.
   * If all predicate checks pass, then return the block's value.
   */
  inline def apply[T](
      inline predicate: => Boolean)(
      inline block: => T): T =
    if !skip && !predicate then fail(predicate, block, true)
    val result = block
    if !skip && !predicate then fail(predicate, block, false)
    result

  inline private def fail[T](
      inline predicate: => Boolean,
      inline block: => T,
      inline before: Boolean): Unit =
    ${ failImpl('predicate, 'block, 'before) }

  case class InvariantFailure(msg: String) extends RuntimeException(msg)

  object InvariantFailure:
    def apply(predicate: String, block: String, before: Boolean) =
      new InvariantFailure(
        s"FAILURE! $predicate failed for block: $block (${beforeAfter(before)} evaluation)")
    private def beforeAfter(before: Boolean) = if before then "before" else "after"

  def failImpl[T](
      predicate: Expr[Boolean], block: Expr[T], before: Expr[Boolean])(
      using Quotes) = '{
        throw InvariantFailure(
          ${showExpr(predicate)},
          ${showExpr(block)},
          $before)
      }

  /* Return a string for the expression */
  private def showExpr[T](expr: Expr[T])(using Quotes): Expr[String] =
    val code: String = expr.show
    Expr(code)
