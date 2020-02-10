// src/main/scala/progscala3/metaprogramming/invariant.scala
package metaprogramming
import scala.quoted._


/**
 * Implement a custom assertion checker. See usage in the test scripts.
 * Unfortunately, this simple implementation only returns "false" for
 * the expression, not the original source code. See this ScalaTest code
 * https://github.com/scalatest/scalatest/tree/3.1.x/scalactic.dotty/src/main/scala/org/scalactic
 * for a more complete (and complex) implementation.
 */
object Requirement {                                                   // <3>
  case class RequirementFailure(
    predicateString: String,
    note: String) extends RuntimeException(
      s"Requirement failure! $predicateString == false. $note")

  /**
   * "Require" that a predicate is true. If not, throw an exception.
   */
  inline def require(
      predicate: => Boolean): Unit = require(predicate, "")

  /**
   * "Require" that a predicate is true. If not, throw an exception
   * with the specified `note` for context.
   */
  inline def require(
      predicate: => Boolean,
      note: String): Unit =
    ${ checkReq('predicate, 'note) }

  def checkReq(predicate: Expr[Boolean], note: Expr[String])(
      using QuoteContext) = '{
    if (!($predicate)) {
      throw new RequirementFailure(${showExpr(predicate)}, ${showExpr(note)})
    }
  }

  def showExpr[T](expr: Expr[T])(using QuoteContext): Expr[String] = {
    val code: String = expr.show
    Expr(code)
  }
}
